package me.zohar.catering.useraccount.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.catering.useraccount.domain.UserAccount;


public interface UserAccountRepo extends JpaRepository<UserAccount, String>, JpaSpecificationExecutor<UserAccount> {
	
	UserAccount findByUserName(String userName);
}
