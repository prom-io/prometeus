package io.realm9.prometheus.dds.services;

import io.realm9.prometheus.dds.services.dds.File;

import java.math.BigDecimal;

public interface BillingService {
    BigDecimal calculateStorageCost(File file, int duration);
}
