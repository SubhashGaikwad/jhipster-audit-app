package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AuditPatientMonitoringFormDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuditPatientMonitoringForm} and its DTO {@link AuditPatientMonitoringFormDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditPatientMonitoringFormMapper extends EntityMapper<AuditPatientMonitoringFormDTO, AuditPatientMonitoringForm> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AuditPatientMonitoringFormDTO toDtoId(AuditPatientMonitoringForm auditPatientMonitoringForm);
}
