package com.car.rent.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rent.domain.Person;

public interface PersonDAO extends  JpaRepository<Person, Long> {

}
