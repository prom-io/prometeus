package io.realm9.prometheus.dds.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jasminb.jsonapi.ResourceConverter;
import io.realm9.prometheus.dds.rest.request.FileExtendRequest;
import io.realm9.prometheus.dds.rest.request.FileUploadRequest;
import io.realm9.prometheus.dds.rest.response.FileResponse;
import io.realm9.prometheus.dds.services.BillingService;
import io.realm9.prometheus.dds.services.DDSService;
import io.realm9.prometheus.dds.services.dds.File;
import io.realm9.prometheus.dds.services.dds.PersistentFile;
import io.realm9.prometheus.dds.services.stubs.BillingServiceStub;
import io.realm9.prometheus.dds.services.stubs.DDSServiceStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Path("/files")
public class FilesStubService {
    private final DDSService ddsService;
    private final BillingService billingService;

    public FilesStubService() {
        ddsService = new DDSServiceStub();
        billingService = new BillingServiceStub();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadFile(FileUploadRequest fileUploadRequest) throws Exception {
        final var temporaryFile = ddsService.storeTemporaryFile(
                fileUploadRequest.getAttributes().getName(),
                fileUploadRequest.getAttributes().getData(),
                fileUploadRequest.getAttributes().getAdditional()
        );
        return getResponse(temporaryFile, fileUploadRequest.getAttributes().getDuration());
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response extendFileDuration(@PathParam("id") String fileId, FileExtendRequest request) throws Exception {
        var file = ddsService.findFileById(UUID.fromString(fileId)).orElseThrow();

        final Map<String, String> map = new HashMap<>();
        map.putAll(file.getMetadata());
        map.putAll(request.getAttributes().getAdditional());
        ((PersistentFile) file).setMetadata(map);

        return getResponse(file, request.getAttributes().getDuration());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/info")
    public Response getFileInfo(@PathParam("id") String fileId) throws Exception {
        var file = (PersistentFile) ddsService.findFileById(UUID.fromString(fileId)).orElseThrow();

        return getOkResponse(file, file.getDuration());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getFile(@PathParam("id") String fileId) {
        return Response.ok("some file data", "binary/octet-stream")
                .build();
    }

    private Response getOkResponse(File file, int duration) throws JsonProcessingException, IllegalAccessException {
        final var resultFile = getFileResponse(file, duration);
        final var resourceConverter = new ResourceConverter("/", FileResponse.class);

        return Response.status(Response.Status.OK)
                .entity(resourceConverter.writeObject(resultFile))
                .build();
    }

    private FileResponse getFileResponse(File file, int duration) {
        final var storageCost = billingService.calculateStorageCost(file, duration);

        var resultFile = FileResponse.builder()
                .additional(file.getMetadata())
                .name(file.getName())
                .duration(duration)
                .price(storageCost)
                .build();

        resultFile.setId(file.getId());
        return resultFile;
    }

    private Response getResponse(File file, int duration) throws JsonProcessingException, IllegalAccessException {
        final var resultFile = getFileResponse(file, duration);
        final var resourceConverter = new ResourceConverter("/", FileResponse.class);

        return Response.status(Response.Status.PAYMENT_REQUIRED)
                .entity(resourceConverter.writeObject(resultFile))
                .build();
    }
}
