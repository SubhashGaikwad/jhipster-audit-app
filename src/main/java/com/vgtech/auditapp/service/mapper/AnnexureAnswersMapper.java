package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AnnexureAnswersDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnnexureAnswers} and its DTO {@link AnnexureAnswersDTO}.
 */
@Mapper(componentModel = "spring", uses = { AuditMapper.class, AnnexureQuestionsMapper.class })
public interface AnnexureAnswersMapper extends EntityMapper<AnnexureAnswersDTO, AnnexureAnswers> {
    @Mapping(target = "audit", source = "audit", qualifiedByName = "id")
    @Mapping(target = "annexureQuestions", source = "annexureQuestions", qualifiedByName = "id")
    AnnexureAnswersDTO toDto(AnnexureAnswers s);
}
