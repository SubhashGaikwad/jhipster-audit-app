import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'question',
        data: { pageTitle: 'auditApp.question.home.title' },
        loadChildren: () => import('./question/question.module').then(m => m.QuestionModule),
      },
      {
        path: 'annexure',
        data: { pageTitle: 'auditApp.annexure.home.title' },
        loadChildren: () => import('./annexure/annexure.module').then(m => m.AnnexureModule),
      },
      {
        path: 'audit-form-s-hosp-gen-info',
        data: { pageTitle: 'auditApp.auditFormSHospGenInfo.home.title' },
        loadChildren: () =>
          import('./audit-form-s-hosp-gen-info/audit-form-s-hosp-gen-info.module').then(m => m.AuditFormSHospGenInfoModule),
      },
      {
        path: 'audit',
        data: { pageTitle: 'auditApp.audit.home.title' },
        loadChildren: () => import('./audit/audit.module').then(m => m.AuditModule),
      },
      {
        path: 'annexure-questions',
        data: { pageTitle: 'auditApp.annexureQuestions.home.title' },
        loadChildren: () => import('./annexure-questions/annexure-questions.module').then(m => m.AnnexureQuestionsModule),
      },
      {
        path: 'annexure-answers',
        data: { pageTitle: 'auditApp.annexureAnswers.home.title' },
        loadChildren: () => import('./annexure-answers/annexure-answers.module').then(m => m.AnnexureAnswersModule),
      },
      {
        path: 'audit-patient-monitoring-form',
        data: { pageTitle: 'auditApp.auditPatientMonitoringForm.home.title' },
        loadChildren: () =>
          import('./audit-patient-monitoring-form/audit-patient-monitoring-form.module').then(m => m.AuditPatientMonitoringFormModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
