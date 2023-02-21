package com.contactManagerSystem;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<Contact , Integer> {
	
	public Contact findByEmailAndPassword(String email , String password);
	
	public Contact findByEmail(String email);
	public int deleteByEmail(String email);
}
