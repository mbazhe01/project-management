package com.mikeba.pma.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mikeba.pma.entities.Address;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {

	@Override
	List<Address> findAll();
		
	
}
