package com.example.demodocker1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demodocker1.entity.Test;

public interface TestRepository extends JpaRepository<Test, String> {

}
