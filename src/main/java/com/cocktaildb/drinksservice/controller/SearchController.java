package com.cocktaildb.drinksservice.controller;

import com.cocktaildb.drinksservice.constants.DrinkServiceConstants;
import com.cocktaildb.drinksservice.dto.SearchResults;
import com.cocktaildb.drinksservice.services.SearchService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cocktailservice/search")
@Slf4j
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/")
    public ResponseEntity<SearchResults> doSearch(@RequestParam(name = "s") String drinkName,
                                                  @RequestParam(name = "count", required = false) Integer count, HttpServletRequest request){

        log.info("GET --> /cocktailservice/search/ -- STARTS with query params s : {} , count : {}",drinkName,count);

        try {
            SearchResults searchResults = searchService.getSearchResults(drinkName,count);

            log.info("GET --> /cocktailservice/search/ -- ENDS");
            return ResponseEntity.status(200).body(searchResults);
        }catch (Exception e){
            log.error("Error in GET --> /cocktailservice/search/ e : {}", e.getMessage());

            return  ResponseEntity.status(500).body(SearchResults.builder()
                            .message(DrinkServiceConstants.COULD_NOT_PROCESS_ERROR)
                    .build());
        }

    }
}
