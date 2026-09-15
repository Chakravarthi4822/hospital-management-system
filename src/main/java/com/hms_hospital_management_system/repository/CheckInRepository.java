package com.hms_hospital_management_system.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms_hospital_management_system.entity.CheckIn;

@Repository
public interface CheckInRepository  extends JpaRepository<CheckIn, Long>{
	
//	@Query("SELECT MAX(c.queueNumber) FROM CheckIn c WHERE c.appointment.doctor.id=:doctorId AND c.checkInTime >=:startOfDay AND c.checkInTime < :endOfDay")
//	Integer findMaxQueueNumber(Long doctorId,LocalDateTime startOfDay,LocalDateTime endOfDay);
}
