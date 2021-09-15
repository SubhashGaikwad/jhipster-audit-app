package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AuditFormSHospGenInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuditFormSHospGenInfo} and its DTO {@link AuditFormSHospGenInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditFormSHospGenInfoMapper extends EntityMapper<AuditFormSHospGenInfoDTO, AuditFormSHospGenInfo> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AuditFormSHospGenInfoDTO toDtoId(AuditFormSHospGenInfo auditFormSHospGenInfo);
}
