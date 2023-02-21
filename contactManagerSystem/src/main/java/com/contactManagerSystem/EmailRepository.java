package com.contactManagerSystem;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import jakarta.transaction.Transactional;

@Transactional
public interface EmailRepository extends CrudRepository<Email , Integer> {
	
	public List<Email> findAllByUseremail(String email);
	public int deleteByUseremailAndContactemail(String userEmail , String contactEmail);
	
}
