package com.cocktaildb.drinksservice.repository;

import com.cocktaildb.drinksservice.entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query,Integer> {

}
