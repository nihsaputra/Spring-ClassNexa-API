package com.enigma.ClassNexa.repository;

import com.enigma.ClassNexa.entity.Attendance;
import com.enigma.ClassNexa.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
    List<Attendance> findByCategoryIn(List<String> category);
    Attendance findByCategory(String category);
}
