package com.brightstraining.springbootblogapplication.repository;

import com.brightstraining.springbootblogapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
