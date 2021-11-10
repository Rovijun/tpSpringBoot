package com.serie.repositories;

import com.serie.models.Serie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends CrudRepository<Serie, Long> {
    @Override
    List<Serie> findAll();
}
