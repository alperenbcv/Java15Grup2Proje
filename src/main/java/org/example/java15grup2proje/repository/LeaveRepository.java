package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Leave;
import org.example.java15grup2proje.entity.enums.ELeaveType;
import org.example.java15grup2proje.entity.enums.EState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, String> {
	public List<Leave> findAllByManagerIdAndLeaveState(String managerId, EState leaveState);
}