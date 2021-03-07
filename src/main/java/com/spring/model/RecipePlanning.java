package com.spring.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "recipe-planning")
public class RecipePlanning {

    @Id
    @Column(name = "RECIPE_KEY")
    private int recipeName;
}
