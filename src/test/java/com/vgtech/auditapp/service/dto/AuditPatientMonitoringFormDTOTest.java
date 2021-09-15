package com.vgtech.auditapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuditPatientMonitoringFormDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AuditPatientMonitoringFormDTO.class);
        AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO1 = new AuditPatientMonitoringFormDTO();
        auditPatientMonitoringFormDTO1.setId(1L);
        AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO2 = new AuditPatientMonitoringFormDTO();
        assertThat(auditPatientMonitoringFormDTO1).isNotEqualTo(auditPatientMonitoringFormDTO2);
        auditPatientMonitoringFormDTO2.setId(auditPatientMonitoringFormDTO1.getId());
        assertThat(auditPatientMonitoringFormDTO1).isEqualTo(auditPatientMonitoringFormDTO2);
        auditPatientMonitoringFormDTO2.setId(2L);
        assertThat(auditPatientMonitoringFormDTO1).isNotEqualTo(auditPatientMonitoringFormDTO2);
        auditPatientMonitoringFormDTO1.setId(null);
        assertThat(auditPatientMonitoringFormDTO1).isNotEqualTo(auditPatientMonitoringFormDTO2);
    }
}
