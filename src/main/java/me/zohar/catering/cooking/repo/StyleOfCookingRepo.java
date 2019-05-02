package me.zohar.catering.cooking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.catering.cooking.domain.StyleOfCooking;

public interface StyleOfCookingRepo
		extends JpaRepository<StyleOfCooking, String>, JpaSpecificationExecutor<StyleOfCooking> {

	List<StyleOfCooking> findByAvailableFlagIsTrueOrderByCreateTimeDesc();

}
