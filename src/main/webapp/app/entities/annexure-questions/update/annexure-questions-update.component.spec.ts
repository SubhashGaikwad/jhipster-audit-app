jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { AnnexureQuestionsService } from '../service/annexure-questions.service';
import { IAnnexureQuestions, AnnexureQuestions } from '../annexure-questions.model';
import { IAudit } from 'app/entities/audit/audit.model';
import { AuditService } from 'app/entities/audit/service/audit.service';

import { AnnexureQuestionsUpdateComponent } from './annexure-questions-update.component';

describe('Component Tests', () => {
  describe('AnnexureQuestions Management Update Component', () => {
    let comp: AnnexureQuestionsUpdateComponent;
    let fixture: ComponentFixture<AnnexureQuestionsUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let annexureQuestionsService: AnnexureQuestionsService;
    let auditService: AuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AnnexureQuestionsUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(AnnexureQuestionsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AnnexureQuestionsUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      annexureQuestionsService = TestBed.inject(AnnexureQuestionsService);
      auditService = TestBed.inject(AuditService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Audit query and add missing value', () => {
        const annexureQuestions: IAnnexureQuestions = { id: 456 };
        const audit: IAudit = { id: 83824 };
        annexureQuestions.audit = audit;

        const auditCollection: IAudit[] = [{ id: 29165 }];
        jest.spyOn(auditService, 'query').mockReturnValue(of(new HttpResponse({ body: auditCollection })));
        const additionalAudits = [audit];
        const expectedCollection: IAudit[] = [...additionalAudits, ...auditCollection];
        jest.spyOn(auditService, 'addAuditToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ annexureQuestions });
        comp.ngOnInit();

        expect(auditService.query).toHaveBeenCalled();
        expect(auditService.addAuditToCollectionIfMissing).toHaveBeenCalledWith(auditCollection, ...additionalAudits);
        expect(comp.auditsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const annexureQuestions: IAnnexureQuestions = { id: 456 };
        const audit: IAudit = { id: 91294 };
        annexureQuestions.audit = audit;

        activatedRoute.data = of({ annexureQuestions });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(annexureQuestions));
        expect(comp.auditsSharedCollection).toContain(audit);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AnnexureQuestions>>();
        const annexureQuestions = { id: 123 };
        jest.spyOn(annexureQuestionsService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ annexureQuestions });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: annexureQuestions }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(annexureQuestionsService.update).toHaveBeenCalledWith(annexureQuestions);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AnnexureQuestions>>();
        const annexureQuestions = new AnnexureQuestions();
        jest.spyOn(annexureQuestionsService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ annexureQuestions });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: annexureQuestions }));
        saveSubject.complete();

        // THEN
        expect(annexureQuestionsService.create).toHaveBeenCalledWith(annexureQuestions);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AnnexureQuestions>>();
        const annexureQuestions = { id: 123 };
        jest.spyOn(annexureQuestionsService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ annexureQuestions });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(annexureQuestionsService.update).toHaveBeenCalledWith(annexureQuestions);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackAuditById', () => {
        it('Should return tracked Audit primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackAuditById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
