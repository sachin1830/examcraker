package com.exam.onlineexam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.onlineexam.model.Question;

@Repository
public interface AdminRepository extends JpaRepository<Question,Integer>
{

}
