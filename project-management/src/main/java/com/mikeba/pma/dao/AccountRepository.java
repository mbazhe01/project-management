package com.mikeba.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.mikeba.pma.entities.UserAccount;

public interface AccountRepository extends CrudRepository<UserAccount, Long> {

}
