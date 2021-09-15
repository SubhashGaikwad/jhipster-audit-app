import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IAnnexureQuestions, AnnexureQuestions } from '../annexure-questions.model';
import { AnnexureQuestionsService } from '../service/annexure-questions.service';
import { IAudit } from 'app/entities/audit/audit.model';
import { AuditService } from 'app/entities/audit/service/audit.service';

@Component({
  selector: 'jhi-annexure-questions-update',
  templateUrl: './annexure-questions-update.component.html',
})
export class AnnexureQuestionsUpdateComponent implements OnInit {
  isSaving = false;

  auditsSharedCollection: IAudit[] = [];

  editForm = this.fb.group({
    id: [],
    formName: [],
    type: [],
    subType: [],
    description: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    freeField4: [],
    createdDate: [],
    createdBy: [],
    lastModified: [],
    lastModifiedBy: [],
    audit: [],
  });

  constructor(
    protected annexureQuestionsService: AnnexureQuestionsService,
    protected auditService: AuditService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ annexureQuestions }) => {
      this.updateForm(annexureQuestions);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const annexureQuestions = this.createFromForm();
    if (annexureQuestions.id !== undefined) {
      this.subscribeToSaveResponse(this.annexureQuestionsService.update(annexureQuestions));
    } else {
      this.subscribeToSaveResponse(this.annexureQuestionsService.create(annexureQuestions));
    }
  }

  trackAuditById(index: number, item: IAudit): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnnexureQuestions>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(annexureQuestions: IAnnexureQuestions): void {
    this.editForm.patchValue({
      id: annexureQuestions.id,
      formName: annexureQuestions.formName,
      type: annexureQuestions.type,
      subType: annexureQuestions.subType,
      description: annexureQuestions.description,
      freeField1: annexureQuestions.freeField1,
      freeField2: annexureQuestions.freeField2,
      freeField3: annexureQuestions.freeField3,
      freeField4: annexureQuestions.freeField4,
      createdDate: annexureQuestions.createdDate,
      createdBy: annexureQuestions.createdBy,
      lastModified: annexureQuestions.lastModified,
      lastModifiedBy: annexureQuestions.lastModifiedBy,
      audit: annexureQuestions.audit,
    });

    this.auditsSharedCollection = this.auditService.addAuditToCollectionIfMissing(this.auditsSharedCollection, annexureQuestions.audit);
  }

  protected loadRelationshipsOptions(): void {
    this.auditService
      .query()
      .pipe(map((res: HttpResponse<IAudit[]>) => res.body ?? []))
      .pipe(map((audits: IAudit[]) => this.auditService.addAuditToCollectionIfMissing(audits, this.editForm.get('audit')!.value)))
      .subscribe((audits: IAudit[]) => (this.auditsSharedCollection = audits));
  }

  protected createFromForm(): IAnnexureQuestions {
    return {
      ...new AnnexureQuestions(),
      id: this.editForm.get(['id'])!.value,
      formName: this.editForm.get(['formName'])!.value,
      type: this.editForm.get(['type'])!.value,
      subType: this.editForm.get(['subType'])!.value,
      description: this.editForm.get(['description'])!.value,
      freeField1: this.editForm.get(['freeField1'])!.value,
      freeField2: this.editForm.get(['freeField2'])!.value,
      freeField3: this.editForm.get(['freeField3'])!.value,
      freeField4: this.editForm.get(['freeField4'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      lastModified: this.editForm.get(['lastModified'])!.value,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value,
      audit: this.editForm.get(['audit'])!.value,
    };
  }
}
