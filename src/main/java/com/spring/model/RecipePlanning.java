package com.spring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "recipe_planning")
public class RecipePlanning {

    @Id
    @Column(name = "recipe_name", unique = true)
    private int recipeName;
}
