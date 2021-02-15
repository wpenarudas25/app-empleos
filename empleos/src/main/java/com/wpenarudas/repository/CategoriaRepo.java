package com.wpenarudas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.wpenarudas.model.Categoria;

@Repository
public interface CategoriaRepo extends CrudRepository<Categoria, Long> {

}
