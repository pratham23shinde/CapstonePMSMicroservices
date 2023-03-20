package com.capstone.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.application.controller.AppointmentController;
import com.capstone.application.dto.appointmentDto;
import com.capstone.application.model.Appointment;
import com.capstone.application.repository.AppointmentRepository;
import com.capstone.application.service.AppointmentService;

import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class AppointmentServiceImpl implements AppointmentService{

	private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(AppointmentServiceImpl.class);

	@Autowired
	private ModelMapper modelmapper;
	
	private AppointmentRepository appointmentRepository;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public Optional<Appointment> findByAppointmentById(Integer patientId) {
		// TODO Auto-generated method stub
		try {
		return appointmentRepository.findById(patientId);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	@Override
	public List<Appointment> findByAppointmentByPEmail(String physicianEmail,String acceptance) {
		// TODO Auto-generated method stub
		try {
		return appointmentRepository.findByEmail(physicianEmail,acceptance);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	@Override
	public boolean deleteAppointment(Integer appointmentId) {
		// TODO Auto-generated method stub
		try {
		appointmentRepository.deleteById(appointmentId);
		return true;
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	@Override
	public List<Appointment> findByAcceptedAppointment(String acceptance) {
		// TODO Auto-generated method stub
		try {
		return appointmentRepository.findByAcceptance(acceptance);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	@Override
	public appointmentDto update(int appointmentId,appointmentDto appointmentdto) {
		try {
		Appointment exisitingAppointment=appointmentRepository.findById(appointmentId).get();
		exisitingAppointment.setAcceptance(appointmentdto.getAcceptance());
		Appointment udpatedAppointment=appointmentRepository.save(exisitingAppointment);
		appointmentdto=modelmapper.map(udpatedAppointment,appointmentDto.class);
		
		return appointmentdto;
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	@Override
	public List<Appointment> findByAppointmentByPEmailandDate(String physicianEmail, String date, String acceptance) {
		// TODO Auto-generated method stub
		try {
		return appointmentRepository.findByEmailandDate(physicianEmail, date, acceptance);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	@Override
	public Appointment saveAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		try {
		appointment.setAcceptance("Pending");
		return appointmentRepository.save(appointment);
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception("Unable to fetch record");
		}
	}

	

	
}
