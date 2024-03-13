package com.ecomPulse.inventoryservice.service;

import org.springframework.transaction.annotation.Transactional;

public interface InventoryService {
    @Transactional(readOnly = true)
    boolean isInStock(String skuCode);
}
