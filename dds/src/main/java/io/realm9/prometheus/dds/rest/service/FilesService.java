package io.realm9.prometheus.dds.rest.service;

import com.github.jasminb.jsonapi.ResourceConverter;
import io.realm9.prometheus.dds.rest.request.FileUploadRequest;
import io.realm9.prometheus.dds.rest.response.FileResponse;
import io.realm9.prometheus.dds.services.BillingService;
import io.realm9.prometheus.dds.services.DDSService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/files")
@RequiredArgsConstructor
public class FilesService {
    @NonNull
    private final DDSService ddsService;
    @NonNull
    private final BillingService billingService;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadFile(FileUploadRequest fileUploadRequest) throws Exception {
        final var temporaryFile = ddsService.storeTemporaryFile(
                fileUploadRequest.getAttributes().getName(),
                fileUploadRequest.getAttributes().getData(),
                fileUploadRequest.getAttributes().getAdditional()
        );
        final var duration = fileUploadRequest.getAttributes().getDuration();
        final var storageCost = billingService.calculateStorageCost(temporaryFile, duration);

        var resultFile = FileResponse.builder()
                .additional(temporaryFile.getMetadata())
                .id(temporaryFile.getId())
                .name(temporaryFile.getName())
                .duration(duration)
                .cost(storageCost)
                .build();

        final var resourceConverter = new ResourceConverter("/", FileResponse.class);

        return Response.status(Response.Status.PAYMENT_REQUIRED)
                .header("Location", "/files/" + temporaryFile.getId() + "/payment")
                .entity(resourceConverter.writeObject(resultFile))
                .build();
    }
}
