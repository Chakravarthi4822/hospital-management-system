package com.hms_hospital_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms_hospital_management_system.entity.Consultation;

@Repository
public interface ConsultationRepository  extends JpaRepository<Consultation, Long>{
	
	

}
