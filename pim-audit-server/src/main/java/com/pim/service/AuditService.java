package com.pim.service;

import com.pim.repository.cassandra.AuditRepository;
import com.pim.repository.cassandra.model.ProductAudit;
import com.pim.repository.cassandra.model.SKUAudit;
import com.pim.utils.AuditHelper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by pkulkar4 on 8/11/18.
 */
@Service
public class AuditService {

    @Value("${com.audit.record.limits:15}")
    int limits;

    @Getter @Setter @Autowired
    AuditRepository auditRepository;

    @Autowired
    AuditHelper helper;

    public void recordProductChanges(ProductAudit audit) {

        if(audit.getUpdatedTime() == null)
            audit.setUpdatedTime(helper.getNowTimeWitoutMicroSec());

        if(audit.getUser() == null || audit.getUser().isEmpty())
            audit.setUser("System");

        getAuditRepository().recordProductChanges(audit);
    }

    public Optional<List<ProductAudit>> getProductAuditRecords(String productId) {
        return getAuditRepository().getProductAuditRecords(productId, limits);
    }

    public void recordSkuChanges(SKUAudit audit) {

        if(audit.getUpdatedTime() == null)
            audit.setUpdatedTime(helper.getNowTimeWitoutMicroSec());

        if(audit.getUser() == null || audit.getUser().isEmpty())
            audit.setUser("System");

        getAuditRepository().recordSkuChanges(audit);
    }

    public Optional<List<SKUAudit>> getSkuAuditRecords(String skuId) {
        return getAuditRepository().getSkuAuditRecords(skuId, limits);
    }
}
