jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { AuditFormSHospGenInfoService } from '../service/audit-form-s-hosp-gen-info.service';
import { IAuditFormSHospGenInfo, AuditFormSHospGenInfo } from '../audit-form-s-hosp-gen-info.model';

import { AuditFormSHospGenInfoUpdateComponent } from './audit-form-s-hosp-gen-info-update.component';

describe('Component Tests', () => {
  describe('AuditFormSHospGenInfo Management Update Component', () => {
    let comp: AuditFormSHospGenInfoUpdateComponent;
    let fixture: ComponentFixture<AuditFormSHospGenInfoUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let auditFormSHospGenInfoService: AuditFormSHospGenInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AuditFormSHospGenInfoUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(AuditFormSHospGenInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AuditFormSHospGenInfoUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      auditFormSHospGenInfoService = TestBed.inject(AuditFormSHospGenInfoService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const auditFormSHospGenInfo: IAuditFormSHospGenInfo = { id: 456 };

        activatedRoute.data = of({ auditFormSHospGenInfo });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(auditFormSHospGenInfo));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AuditFormSHospGenInfo>>();
        const auditFormSHospGenInfo = { id: 123 };
        jest.spyOn(auditFormSHospGenInfoService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ auditFormSHospGenInfo });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: auditFormSHospGenInfo }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(auditFormSHospGenInfoService.update).toHaveBeenCalledWith(auditFormSHospGenInfo);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AuditFormSHospGenInfo>>();
        const auditFormSHospGenInfo = new AuditFormSHospGenInfo();
        jest.spyOn(auditFormSHospGenInfoService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ auditFormSHospGenInfo });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: auditFormSHospGenInfo }));
        saveSubject.complete();

        // THEN
        expect(auditFormSHospGenInfoService.create).toHaveBeenCalledWith(auditFormSHospGenInfo);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AuditFormSHospGenInfo>>();
        const auditFormSHospGenInfo = { id: 123 };
        jest.spyOn(auditFormSHospGenInfoService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ auditFormSHospGenInfo });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(auditFormSHospGenInfoService.update).toHaveBeenCalledWith(auditFormSHospGenInfo);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
