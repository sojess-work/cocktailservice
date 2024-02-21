package com.cocktaildb.drinksservice.controller.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "query")
@Data
@Builder
public class Query {

    @Id()
    private int id;

    @Column(name = "query_type")
    private String queryType;

    @Column(name = "query_param")
    private String queryParam;

    @Column(name = "status")
    private String status;


}
