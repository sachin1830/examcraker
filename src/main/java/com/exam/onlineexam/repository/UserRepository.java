package com.exam.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.onlineexam.model.RegisterUser;

@Repository
public interface UserRepository extends JpaRepository<RegisterUser,Long>
{
	RegisterUser findByEmail(String email);
}
