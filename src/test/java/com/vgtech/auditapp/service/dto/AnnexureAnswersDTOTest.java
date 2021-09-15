package com.vgtech.auditapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnnexureAnswersDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnnexureAnswersDTO.class);
        AnnexureAnswersDTO annexureAnswersDTO1 = new AnnexureAnswersDTO();
        annexureAnswersDTO1.setId(1L);
        AnnexureAnswersDTO annexureAnswersDTO2 = new AnnexureAnswersDTO();
        assertThat(annexureAnswersDTO1).isNotEqualTo(annexureAnswersDTO2);
        annexureAnswersDTO2.setId(annexureAnswersDTO1.getId());
        assertThat(annexureAnswersDTO1).isEqualTo(annexureAnswersDTO2);
        annexureAnswersDTO2.setId(2L);
        assertThat(annexureAnswersDTO1).isNotEqualTo(annexureAnswersDTO2);
        annexureAnswersDTO1.setId(null);
        assertThat(annexureAnswersDTO1).isNotEqualTo(annexureAnswersDTO2);
    }
}
