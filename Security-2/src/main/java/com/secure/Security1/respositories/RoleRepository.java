package com.secure.Security1.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.secure.Security1.model.ERole;
import com.secure.Security1.model.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Optional<Role> findByName(ERole name);
}
