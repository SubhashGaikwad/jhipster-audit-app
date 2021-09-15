package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AuditDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Audit} and its DTO {@link AuditDTO}.
 */
@Mapper(componentModel = "spring", uses = { AuditPatientMonitoringFormMapper.class, AuditFormSHospGenInfoMapper.class })
public interface AuditMapper extends EntityMapper<AuditDTO, Audit> {
    @Mapping(target = "auditPatientMonitoringForm", source = "auditPatientMonitoringForm", qualifiedByName = "id")
    @Mapping(target = "auditFormSHospGenInfo", source = "auditFormSHospGenInfo", qualifiedByName = "id")
    AuditDTO toDto(Audit s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AuditDTO toDtoId(Audit audit);
}
