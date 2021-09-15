package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.AuditPatientMonitoringFormRepository;
import com.vgtech.auditapp.service.AuditPatientMonitoringFormQueryService;
import com.vgtech.auditapp.service.AuditPatientMonitoringFormService;
import com.vgtech.auditapp.service.criteria.AuditPatientMonitoringFormCriteria;
import com.vgtech.auditapp.service.dto.AuditPatientMonitoringFormDTO;
import com.vgtech.auditapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.vgtech.auditapp.domain.AuditPatientMonitoringForm}.
 */
@RestController
@RequestMapping("/api")
public class AuditPatientMonitoringFormResource {

    private final Logger log = LoggerFactory.getLogger(AuditPatientMonitoringFormResource.class);

    private static final String ENTITY_NAME = "auditPatientMonitoringForm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditPatientMonitoringFormService auditPatientMonitoringFormService;

    private final AuditPatientMonitoringFormRepository auditPatientMonitoringFormRepository;

    private final AuditPatientMonitoringFormQueryService auditPatientMonitoringFormQueryService;

    public AuditPatientMonitoringFormResource(
        AuditPatientMonitoringFormService auditPatientMonitoringFormService,
        AuditPatientMonitoringFormRepository auditPatientMonitoringFormRepository,
        AuditPatientMonitoringFormQueryService auditPatientMonitoringFormQueryService
    ) {
        this.auditPatientMonitoringFormService = auditPatientMonitoringFormService;
        this.auditPatientMonitoringFormRepository = auditPatientMonitoringFormRepository;
        this.auditPatientMonitoringFormQueryService = auditPatientMonitoringFormQueryService;
    }

    /**
     * {@code POST  /audit-patient-monitoring-forms} : Create a new auditPatientMonitoringForm.
     *
     * @param auditPatientMonitoringFormDTO the auditPatientMonitoringFormDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditPatientMonitoringFormDTO, or with status {@code 400 (Bad Request)} if the auditPatientMonitoringForm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/audit-patient-monitoring-forms")
    public ResponseEntity<AuditPatientMonitoringFormDTO> createAuditPatientMonitoringForm(
        @RequestBody AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AuditPatientMonitoringForm : {}", auditPatientMonitoringFormDTO);
        if (auditPatientMonitoringFormDTO.getId() != null) {
            throw new BadRequestAlertException("A new auditPatientMonitoringForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuditPatientMonitoringFormDTO result = auditPatientMonitoringFormService.save(auditPatientMonitoringFormDTO);
        return ResponseEntity
            .created(new URI("/api/audit-patient-monitoring-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /audit-patient-monitoring-forms/:id} : Updates an existing auditPatientMonitoringForm.
     *
     * @param id the id of the auditPatientMonitoringFormDTO to save.
     * @param auditPatientMonitoringFormDTO the auditPatientMonitoringFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditPatientMonitoringFormDTO,
     * or with status {@code 400 (Bad Request)} if the auditPatientMonitoringFormDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditPatientMonitoringFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/audit-patient-monitoring-forms/{id}")
    public ResponseEntity<AuditPatientMonitoringFormDTO> updateAuditPatientMonitoringForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AuditPatientMonitoringForm : {}, {}", id, auditPatientMonitoringFormDTO);
        if (auditPatientMonitoringFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditPatientMonitoringFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditPatientMonitoringFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AuditPatientMonitoringFormDTO result = auditPatientMonitoringFormService.save(auditPatientMonitoringFormDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditPatientMonitoringFormDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code PATCH  /audit-patient-monitoring-forms/:id} : Partial updates given fields of an existing auditPatientMonitoringForm, field will ignore if it is null
     *
     * @param id the id of the auditPatientMonitoringFormDTO to save.
     * @param auditPatientMonitoringFormDTO the auditPatientMonitoringFormDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditPatientMonitoringFormDTO,
     * or with status {@code 400 (Bad Request)} if the auditPatientMonitoringFormDTO is not valid,
     * or with status {@code 404 (Not Found)} if the auditPatientMonitoringFormDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the auditPatientMonitoringFormDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/audit-patient-monitoring-forms/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AuditPatientMonitoringFormDTO> partialUpdateAuditPatientMonitoringForm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AuditPatientMonitoringForm partially : {}, {}", id, auditPatientMonitoringFormDTO);
        if (auditPatientMonitoringFormDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditPatientMonitoringFormDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditPatientMonitoringFormRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AuditPatientMonitoringFormDTO> result = auditPatientMonitoringFormService.partialUpdate(auditPatientMonitoringFormDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditPatientMonitoringFormDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /audit-patient-monitoring-forms} : get all the auditPatientMonitoringForms.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditPatientMonitoringForms in body.
     */
    @GetMapping("/audit-patient-monitoring-forms")
    public ResponseEntity<List<AuditPatientMonitoringFormDTO>> getAllAuditPatientMonitoringForms(
        AuditPatientMonitoringFormCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get AuditPatientMonitoringForms by criteria: {}", criteria);
        Page<AuditPatientMonitoringFormDTO> page = auditPatientMonitoringFormQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /audit-patient-monitoring-forms/count} : count all the auditPatientMonitoringForms.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/audit-patient-monitoring-forms/count")
    public ResponseEntity<Long> countAuditPatientMonitoringForms(AuditPatientMonitoringFormCriteria criteria) {
        log.debug("REST request to count AuditPatientMonitoringForms by criteria: {}", criteria);
        return ResponseEntity.ok().body(auditPatientMonitoringFormQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /audit-patient-monitoring-forms/:id} : get the "id" auditPatientMonitoringForm.
     *
     * @param id the id of the auditPatientMonitoringFormDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditPatientMonitoringFormDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/audit-patient-monitoring-forms/{id}")
    public ResponseEntity<AuditPatientMonitoringFormDTO> getAuditPatientMonitoringForm(@PathVariable Long id) {
        log.debug("REST request to get AuditPatientMonitoringForm : {}", id);
        Optional<AuditPatientMonitoringFormDTO> auditPatientMonitoringFormDTO = auditPatientMonitoringFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(auditPatientMonitoringFormDTO);
    }

    /**
     * {@code DELETE  /audit-patient-monitoring-forms/:id} : delete the "id" auditPatientMonitoringForm.
     *
     * @param id the id of the auditPatientMonitoringFormDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/audit-patient-monitoring-forms/{id}")
    public ResponseEntity<Void> deleteAuditPatientMonitoringForm(@PathVariable Long id) {
        log.debug("REST request to delete AuditPatientMonitoringForm : {}", id);
        auditPatientMonitoringFormService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
