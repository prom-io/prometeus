package io.realm9.prometheus.dds.services.stubs;

import io.realm9.prometheus.dds.services.BillingService;
import io.realm9.prometheus.dds.services.dds.File;

import java.math.BigDecimal;

public class BillingServiceStub implements BillingService {
    @Override
    public BigDecimal calculateStorageCost(File file, int duration) {
        return BigDecimal.valueOf(100.50);
    }
}
