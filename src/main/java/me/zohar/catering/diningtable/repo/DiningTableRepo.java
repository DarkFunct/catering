package me.zohar.catering.diningtable.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.catering.diningtable.domain.DiningTable;

public interface DiningTableRepo extends JpaRepository<DiningTable, String>, JpaSpecificationExecutor<DiningTable> {

	List<DiningTable> findByStateAndAvailableFlagIsTrue(String state);

}
