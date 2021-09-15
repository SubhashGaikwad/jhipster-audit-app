package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AnnexureQuestionsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnnexureQuestions} and its DTO {@link AnnexureQuestionsDTO}.
 */
@Mapper(componentModel = "spring", uses = { AuditMapper.class })
public interface AnnexureQuestionsMapper extends EntityMapper<AnnexureQuestionsDTO, AnnexureQuestions> {
    @Mapping(target = "audit", source = "audit", qualifiedByName = "id")
    AnnexureQuestionsDTO toDto(AnnexureQuestions s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AnnexureQuestionsDTO toDtoId(AnnexureQuestions annexureQuestions);
}
