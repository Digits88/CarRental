package com.car.rent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.car.rent.domain.Account;
public interface AccountDAO extends JpaRepository<Account, Long> {

}
