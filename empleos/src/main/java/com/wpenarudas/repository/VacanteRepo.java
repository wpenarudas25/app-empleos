package com.wpenarudas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wpenarudas.model.Vacante;

@Repository
public interface VacanteRepo  extends CrudRepository<Vacante, Long>{

}
