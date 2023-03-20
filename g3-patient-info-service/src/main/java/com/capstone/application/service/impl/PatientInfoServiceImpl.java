package com.capstone.application.service.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.application.dto.PatientDto;
import com.capstone.application.model.Patient;
import com.capstone.application.repository.PatientInfoRepository;
import com.capstone.application.service.PatientInfoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PatientInfoServiceImpl implements PatientInfoService
{
	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(PatientInfoServiceImpl.class);

	@Autowired
	private ModelMapper modelmapper;

	private PatientInfoRepository patientInfoRepository;
	
	public PatientInfoServiceImpl(PatientInfoRepository patientInfoRepository) {
		super();
		this.patientInfoRepository = patientInfoRepository;
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		try {
		return patientInfoRepository.findAll();
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}


	@Override
	public PatientDto displayPatientById(Integer patientId) {
		try {
		Patient patient = patientInfoRepository.findById(patientId).get();
		return modelmapper.map(patient,PatientDto.class);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	@Override
	public PatientDto updatePatient(int patientId, PatientDto patientDto) {
		try {
		Patient existingPatient = patientInfoRepository.findById(patientId).get();
		existingPatient.setEmail(patientDto.getEmail());
		existingPatient.setTitle(patientDto.getTitle());
		existingPatient.setFirstName(patientDto.getFirstName());
		existingPatient.setLastName(patientDto.getLastName());
		existingPatient.setDob(patientDto.getDob());
		existingPatient.setContactNumber(patientDto.getContactNumber());
		existingPatient.setPassword(patientDto.getPassword());
		existingPatient.setGender(patientDto.getGender());
		existingPatient.setAddress(patientDto.getAddress());
		Patient updatedPatient=patientInfoRepository.save(existingPatient);
		patientDto=modelmapper.map(updatedPatient,PatientDto.class);

		return patientDto;
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}
	
	
	
}
