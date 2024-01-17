package com.beck.springbootebookaws.repository;

import com.beck.springbootebookaws.db.entity.HistoryOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryOperationRepository extends JpaRepository<HistoryOperation,Long> {

}
