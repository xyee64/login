package com.secure.Security1.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.secure.Security1.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	

}
