package com.spring.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel
@Entity(name = "RecipePlanning")
@Table(name = "recipe_planning")
public class RecipePlanning implements Serializable {

    @ApiModelProperty(value = "Linked recipe name.", example = "Apfelkompott")
    @Id
    @Column(name = "recipe_name", nullable = false)
    private String recipeName;

    @ApiModelProperty(value = "Reference to the recipe.", example = "Apfelkompott")
    //@MapsId //TODO -> will create the table with a foreign key, but crash the repository
    @OneToOne
    @JoinColumn(name = "recipe_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Recipe recipe;
}
