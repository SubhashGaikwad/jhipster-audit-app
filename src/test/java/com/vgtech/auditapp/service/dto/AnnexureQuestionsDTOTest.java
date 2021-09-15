package com.vgtech.auditapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnnexureQuestionsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnnexureQuestionsDTO.class);
        AnnexureQuestionsDTO annexureQuestionsDTO1 = new AnnexureQuestionsDTO();
        annexureQuestionsDTO1.setId(1L);
        AnnexureQuestionsDTO annexureQuestionsDTO2 = new AnnexureQuestionsDTO();
        assertThat(annexureQuestionsDTO1).isNotEqualTo(annexureQuestionsDTO2);
        annexureQuestionsDTO2.setId(annexureQuestionsDTO1.getId());
        assertThat(annexureQuestionsDTO1).isEqualTo(annexureQuestionsDTO2);
        annexureQuestionsDTO2.setId(2L);
        assertThat(annexureQuestionsDTO1).isNotEqualTo(annexureQuestionsDTO2);
        annexureQuestionsDTO1.setId(null);
        assertThat(annexureQuestionsDTO1).isNotEqualTo(annexureQuestionsDTO2);
    }
}
