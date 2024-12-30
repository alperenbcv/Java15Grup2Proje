package org.example.java15grup2proje.repository;

import org.example.java15grup2proje.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
	List<Expense> findAllByPersonnelId(String id);
}