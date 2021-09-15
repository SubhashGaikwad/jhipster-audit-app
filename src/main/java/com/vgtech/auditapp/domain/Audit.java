package com.vgtech.auditapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Audit.
 */
@Entity
@Table(name = "audit")
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "audit_date")
    private LocalDate auditDate;

    @Column(name = "hosp_name")
    private String hospName;

    @Column(name = "is_audit_complete")
    private Boolean isAuditComplete;

    @Column(name = "free_field_1")
    private String freeField1;

    @Column(name = "free_field_2")
    private String freeField2;

    @Column(name = "free_field_3")
    private String freeField3;

    @Column(name = "free_field_4")
    private String freeField4;

    @Column(name = "remark")
    private String remark;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "last_modified")
    private LocalDate lastModified;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @JsonIgnoreProperties(value = { "audit" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private AuditPatientMonitoringForm auditPatientMonitoringForm;

    @OneToMany(mappedBy = "audit")
    @JsonIgnoreProperties(value = { "audit", "annexureQuestions" }, allowSetters = true)
    private Set<AnnexureAnswers> annexureAnswers = new HashSet<>();

    @OneToMany(mappedBy = "audit")
    @JsonIgnoreProperties(value = { "annexureAnswers", "audit" }, allowSetters = true)
    private Set<AnnexureQuestions> annexureQuestions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "audits" }, allowSetters = true)
    private AuditFormSHospGenInfo auditFormSHospGenInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Audit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAuditDate() {
        return this.auditDate;
    }

    public Audit auditDate(LocalDate auditDate) {
        this.setAuditDate(auditDate);
        return this;
    }

    public void setAuditDate(LocalDate auditDate) {
        this.auditDate = auditDate;
    }

    public String getHospName() {
        return this.hospName;
    }

    public Audit hospName(String hospName) {
        this.setHospName(hospName);
        return this;
    }

    public void setHospName(String hospName) {
        this.hospName = hospName;
    }

    public Boolean getIsAuditComplete() {
        return this.isAuditComplete;
    }

    public Audit isAuditComplete(Boolean isAuditComplete) {
        this.setIsAuditComplete(isAuditComplete);
        return this;
    }

    public void setIsAuditComplete(Boolean isAuditComplete) {
        this.isAuditComplete = isAuditComplete;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public Audit freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public Audit freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public Audit freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public Audit freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getRemark() {
        return this.remark;
    }

    public Audit remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Audit createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedDate() {
        return this.createdDate;
    }

    public Audit createdDate(LocalDate createdDate) {
        this.setCreatedDate(createdDate);
        return this;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastModified() {
        return this.lastModified;
    }

    public Audit lastModified(LocalDate lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public Audit lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public AuditPatientMonitoringForm getAuditPatientMonitoringForm() {
        return this.auditPatientMonitoringForm;
    }

    public void setAuditPatientMonitoringForm(AuditPatientMonitoringForm auditPatientMonitoringForm) {
        this.auditPatientMonitoringForm = auditPatientMonitoringForm;
    }

    public Audit auditPatientMonitoringForm(AuditPatientMonitoringForm auditPatientMonitoringForm) {
        this.setAuditPatientMonitoringForm(auditPatientMonitoringForm);
        return this;
    }

    public Set<AnnexureAnswers> getAnnexureAnswers() {
        return this.annexureAnswers;
    }

    public void setAnnexureAnswers(Set<AnnexureAnswers> annexureAnswers) {
        if (this.annexureAnswers != null) {
            this.annexureAnswers.forEach(i -> i.setAudit(null));
        }
        if (annexureAnswers != null) {
            annexureAnswers.forEach(i -> i.setAudit(this));
        }
        this.annexureAnswers = annexureAnswers;
    }

    public Audit annexureAnswers(Set<AnnexureAnswers> annexureAnswers) {
        this.setAnnexureAnswers(annexureAnswers);
        return this;
    }

    public Audit addAnnexureAnswers(AnnexureAnswers annexureAnswers) {
        this.annexureAnswers.add(annexureAnswers);
        annexureAnswers.setAudit(this);
        return this;
    }

    public Audit removeAnnexureAnswers(AnnexureAnswers annexureAnswers) {
        this.annexureAnswers.remove(annexureAnswers);
        annexureAnswers.setAudit(null);
        return this;
    }

    public Set<AnnexureQuestions> getAnnexureQuestions() {
        return this.annexureQuestions;
    }

    public void setAnnexureQuestions(Set<AnnexureQuestions> annexureQuestions) {
        if (this.annexureQuestions != null) {
            this.annexureQuestions.forEach(i -> i.setAudit(null));
        }
        if (annexureQuestions != null) {
            annexureQuestions.forEach(i -> i.setAudit(this));
        }
        this.annexureQuestions = annexureQuestions;
    }

    public Audit annexureQuestions(Set<AnnexureQuestions> annexureQuestions) {
        this.setAnnexureQuestions(annexureQuestions);
        return this;
    }

    public Audit addAnnexureQuestions(AnnexureQuestions annexureQuestions) {
        this.annexureQuestions.add(annexureQuestions);
        annexureQuestions.setAudit(this);
        return this;
    }

    public Audit removeAnnexureQuestions(AnnexureQuestions annexureQuestions) {
        this.annexureQuestions.remove(annexureQuestions);
        annexureQuestions.setAudit(null);
        return this;
    }

    public AuditFormSHospGenInfo getAuditFormSHospGenInfo() {
        return this.auditFormSHospGenInfo;
    }

    public void setAuditFormSHospGenInfo(AuditFormSHospGenInfo auditFormSHospGenInfo) {
        this.auditFormSHospGenInfo = auditFormSHospGenInfo;
    }

    public Audit auditFormSHospGenInfo(AuditFormSHospGenInfo auditFormSHospGenInfo) {
        this.setAuditFormSHospGenInfo(auditFormSHospGenInfo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Audit)) {
            return false;
        }
        return id != null && id.equals(((Audit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Audit{" +
            "id=" + getId() +
            ", auditDate='" + getAuditDate() + "'" +
            ", hospName='" + getHospName() + "'" +
            ", isAuditComplete='" + getIsAuditComplete() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", remark='" + getRemark() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            "}";
    }
}
