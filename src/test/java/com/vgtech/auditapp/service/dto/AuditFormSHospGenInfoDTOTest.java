package com.vgtech.auditapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuditFormSHospGenInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditFormSHospGenInfoDTO.class);
        AuditFormSHospGenInfoDTO auditFormSHospGenInfoDTO1 = new AuditFormSHospGenInfoDTO();
        auditFormSHospGenInfoDTO1.setId(1L);
        AuditFormSHospGenInfoDTO auditFormSHospGenInfoDTO2 = new AuditFormSHospGenInfoDTO();
        assertThat(auditFormSHospGenInfoDTO1).isNotEqualTo(auditFormSHospGenInfoDTO2);
        auditFormSHospGenInfoDTO2.setId(auditFormSHospGenInfoDTO1.getId());
        assertThat(auditFormSHospGenInfoDTO1).isEqualTo(auditFormSHospGenInfoDTO2);
        auditFormSHospGenInfoDTO2.setId(2L);
        assertThat(auditFormSHospGenInfoDTO1).isNotEqualTo(auditFormSHospGenInfoDTO2);
        auditFormSHospGenInfoDTO1.setId(null);
        assertThat(auditFormSHospGenInfoDTO1).isNotEqualTo(auditFormSHospGenInfoDTO2);
    }
}
