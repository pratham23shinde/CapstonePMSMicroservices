package com.capstone.application.service.impl;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capstone.application.dto.PhysicianAvailabilityDto;
import com.capstone.application.model.PhysicianAvailabilityModel;
import com.capstone.application.repository.PhysicianAvailabilityRepository;
import com.capstone.application.service.PhysicianAvailabilityService;


@Service
public class PhysicianAvailabilityServiceImpl implements PhysicianAvailabilityService{
	
	@Autowired
	private ModelMapper modelmapper;
	
	private PhysicianAvailabilityRepository physicianAvailabilityRepository;

	public PhysicianAvailabilityServiceImpl(PhysicianAvailabilityRepository physicianAvailabilityRepository) {
		super();
		this.physicianAvailabilityRepository = physicianAvailabilityRepository;
	}

	@Override
	public List<PhysicianAvailabilityModel> findAll() {
		// TODO Auto-generated method stub
		return physicianAvailabilityRepository.findAll();
	}
	
	@Override
	public boolean deletePhysician(String physicianEmail) {
		// TODO Auto-generated method stub
		physicianAvailabilityRepository.deleteById(physicianEmail);
		return true;
	}

	@Override
	public PhysicianAvailabilityDto createAvailability(PhysicianAvailabilityDto physicianAvailabilityDto) {
		
		
		PhysicianAvailabilityModel physicianAvailabiity=modelmapper.map(physicianAvailabilityDto, PhysicianAvailabilityModel.class);
		PhysicianAvailabilityModel savedAvailability=physicianAvailabilityRepository.save(physicianAvailabiity);
		PhysicianAvailabilityDto savedAvailabilityDto=modelmapper.map(savedAvailability, PhysicianAvailabilityDto.class);
		return savedAvailabilityDto;
	}

	@Override
	public PhysicianAvailabilityDto updateAvailabilty(String physicianEmail,PhysicianAvailabilityDto physicianAvailabilityDto) {

		PhysicianAvailabilityModel currentAvailability = physicianAvailabilityRepository.findById(physicianEmail).get();
		currentAvailability.setFromDate(physicianAvailabilityDto.getFromDate());
		currentAvailability.setToDate(physicianAvailabilityDto.getToDate());
		currentAvailability.setAvailability(physicianAvailabilityDto.isAvailability());
		PhysicianAvailabilityModel updatedAvailability=physicianAvailabilityRepository.save(currentAvailability);
		physicianAvailabilityDto=modelmapper.map(updatedAvailability,PhysicianAvailabilityDto.class);
		return physicianAvailabilityDto;
		
	}

	
}
