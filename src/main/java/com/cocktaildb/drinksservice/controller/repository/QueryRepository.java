package com.cocktaildb.drinksservice.controller.repository;

import com.cocktaildb.drinksservice.controller.entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query,Integer> {

}
