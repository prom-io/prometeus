package io.realm9.prometheus.dds.rest.service;

import com.github.jasminb.jsonapi.ResourceConverter;
import io.realm9.prometheus.dds.rest.request.PaymentRequest;
import io.realm9.prometheus.dds.rest.response.LocalPaymentResponse;
import io.realm9.prometheus.dds.rest.response.PaymentResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Path("/payments")
public class PaymentsStubService {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPayment(PaymentRequest paymentRequest) throws Exception {
        final var resourceConverter = new ResourceConverter("/", PaymentResponse.class);
        final var entity = PaymentResponse.builder()
                .amount(paymentRequest.getAttributes().getAmount())
                .file_id(UUID.fromString(paymentRequest.getAttributes().getFile_id()))
                .status(paymentRequest.getAttributes().getStatus())
                .build();

        entity.setId(UUID.randomUUID());

        return Response.status(Response.Status.CREATED)
                .header("Location", "/payments/" + entity.getId())
                .entity(resourceConverter.writeObject(entity))
                .build();
    }

    @GET
    @Path("local")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocalPayment(@QueryParam("from") String from, @QueryParam("to") String to) throws Exception {
        var df = new SimpleDateFormat("yyyyMMdd");
        var fromDate = df.parse(from);
        var toDate = df.parse(to);

        final var resourceConverter = new ResourceConverter("/", LocalPaymentResponse.class);
        final var entity = LocalPaymentResponse.builder()
                .from(df.format(fromDate))
                .to(df.format(toDate))
                .amount(BigDecimal.valueOf(500.23))
                .size(10020033)
                .build();

        entity.setId(UUID.randomUUID());

        return Response.status(Response.Status.CREATED)
                .entity(resourceConverter.writeObject(entity))
                .build();
    }
}
