package com.cocktaildb.drinksservice.services;

import com.cocktaildb.drinksservice.controller.entity.Query;
import com.cocktaildb.drinksservice.controller.repository.QueryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QueryService {

    @Autowired
    QueryRepository queryRepository;
    public void saveQueryDetailsToDb(String queryType, List<String> queryParams,String status){

    StringBuilder paramsBuilder = new StringBuilder();
        if(!queryParams.isEmpty()){
            queryParams.forEach(paramsBuilder::append);
        }

        Query query = Query.builder()
                .queryParam(paramsBuilder.toString())
                .queryType(queryType)
                .status(status)
                .build();

//        queryRepository.save(query);

    }

}
