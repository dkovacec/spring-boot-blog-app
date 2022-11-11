package com.brightstraining.springbootblogapplication.repository;

import com.brightstraining.springbootblogapplication.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {


}