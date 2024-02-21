package com.cocktaildb.drinksservice.services;

import com.cocktaildb.drinksservice.config.ApplicationProperties;
import com.cocktaildb.drinksservice.dto.DrinkDto;
import com.cocktaildb.drinksservice.dto.SearchResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ApplicationProperties applicationProperties;

    @Autowired
    QueryService queryService;

    @Value("${defaultDrinksListSize}")
    private int defaultListSize;

    public SearchResults getSearchResults(String drinkName, Integer count){

        log.info("Inside getSearchResults() method");

        SearchResults searchResults = callCockTailDb(drinkName);
        List<DrinkDto> drinks =  searchResults.getDrinks();
        List<String> queryParams = List.of(drinkName, (count != null ? count.toString() : "0"));

        if(count !=null && count !=0){

        if(drinks != null && count <= drinks.size()){
            List<DrinkDto> requestedDrinks = drinks.subList(0,count);
            searchResults.setDrinks(requestedDrinks);
            queryService.saveQueryDetailsToDb("search",queryParams,"success");
            return  searchResults;
        }

        }else {
            if(drinks != null && defaultListSize <= drinks.size()){
                List<DrinkDto> requestedDrinks = drinks.subList(0,defaultListSize);
                searchResults.setDrinks(requestedDrinks);
                queryService.saveQueryDetailsToDb("search",queryParams,"success");
                return  searchResults;
            }
        }
        queryService.saveQueryDetailsToDb("search",queryParams,"failed");
        searchResults.setMessage("Search query doesn't met the criteria");
        return  searchResults;
    }


    private SearchResults callCockTailDb(String searchTerm){
        SearchResults searchResults = new SearchResults();
        log.info("Inside callCockTailDb() calling cocktail db service with search term {}",searchTerm);

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> httpEntity = new HttpEntity<>(headers);
            String url = MessageFormat.format(applicationProperties.getSearchDrinksUrl(),searchTerm);
            ResponseEntity<SearchResults>  response = restTemplate.exchange(url, HttpMethod.GET,httpEntity,SearchResults.class);
            if(response != null && response.getBody() !=null && response.getBody().getDrinks() !=null ){
               searchResults = response.getBody();
                log.info("Successfully  fetched cocktail data with search query {} and with a size  {}",searchTerm, response.getBody().getDrinks().size());
               return  searchResults;
            }else {
                return  searchResults;
            }
        }catch (Exception e){
            log.error("Error retrieving details  from callCockTailDb e : {}", e.getMessage());
            return searchResults;
        }

    }

}
