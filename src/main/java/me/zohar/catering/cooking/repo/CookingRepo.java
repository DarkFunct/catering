package me.zohar.catering.cooking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.catering.cooking.domain.Cooking;

public interface CookingRepo extends JpaRepository<Cooking, String>, JpaSpecificationExecutor<Cooking> {
	
	List<Cooking> findByStyleOfCookingIdAndAvailableFlagIsTrue(String styleOfCookingId);

}
