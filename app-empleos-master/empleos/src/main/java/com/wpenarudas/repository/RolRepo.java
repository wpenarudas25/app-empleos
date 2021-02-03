package com.wpenarudas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wpenarudas.model.Role;

@Repository
public interface RolRepo extends CrudRepository<Role, Long> {

}
