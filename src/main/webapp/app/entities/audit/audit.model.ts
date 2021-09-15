import * as dayjs from 'dayjs';
import { IAuditPatientMonitoringForm } from 'app/entities/audit-patient-monitoring-form/audit-patient-monitoring-form.model';
import { IAnnexureAnswers } from 'app/entities/annexure-answers/annexure-answers.model';
import { IAnnexureQuestions } from 'app/entities/annexure-questions/annexure-questions.model';
import { IAuditFormSHospGenInfo } from 'app/entities/audit-form-s-hosp-gen-info/audit-form-s-hosp-gen-info.model';

export interface IAudit {
  id?: number;
  auditDate?: dayjs.Dayjs | null;
  hospName?: string | null;
  isAuditComplete?: boolean | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  remark?: string | null;
  createdBy?: string | null;
  createdDate?: dayjs.Dayjs | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  auditPatientMonitoringForm?: IAuditPatientMonitoringForm | null;
  annexureAnswers?: IAnnexureAnswers[] | null;
  annexureQuestions?: IAnnexureQuestions[] | null;
  auditFormSHospGenInfo?: IAuditFormSHospGenInfo | null;
}

export class Audit implements IAudit {
  constructor(
    public id?: number,
    public auditDate?: dayjs.Dayjs | null,
    public hospName?: string | null,
    public isAuditComplete?: boolean | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public remark?: string | null,
    public createdBy?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public lastModified?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public auditPatientMonitoringForm?: IAuditPatientMonitoringForm | null,
    public annexureAnswers?: IAnnexureAnswers[] | null,
    public annexureQuestions?: IAnnexureQuestions[] | null,
    public auditFormSHospGenInfo?: IAuditFormSHospGenInfo | null
  ) {
    this.isAuditComplete = this.isAuditComplete ?? false;
  }
}

export function getAuditIdentifier(audit: IAudit): number | undefined {
  return audit.id;
}
