package com.capstone.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.capstone.application.dto.PatientDto;
import com.capstone.application.model.Patient;
import com.capstone.application.service.PatientInfoService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class PatientInfoController {
	
	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(PatientInfoController.class);

	
	private PatientInfoService patientInfoService;

	public PatientInfoController(PatientInfoService patientInfoService) 
	{
		super();
		this.patientInfoService = patientInfoService;
	}

	
	@GetMapping("/patient/{patientId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public PatientDto getPatientById(@PathVariable int patientId) 
	{
     try {   
		return patientInfoService.displayPatientById(patientId);
     }
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new HttpClientErrorException(HttpStatusCode.valueOf(500));
		}
	}
	
	@PutMapping("/patient/{patientId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	 public PatientDto updatePatientInfo(@PathVariable int patientId, @RequestBody PatientDto patientDto) 
	{
       try {
		return patientInfoService.updatePatient(patientId, patientDto);
       }
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new HttpClientErrorException(HttpStatusCode.valueOf(500));
		}
	}

	
	@GetMapping("/patient")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Patient> PatientList() {
		try {
		List < Patient > patient = patientInfoService.findAll();
		return patient;
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new HttpClientErrorException(HttpStatusCode.valueOf(500));
		}
	}

}
