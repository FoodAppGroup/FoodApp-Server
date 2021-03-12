package com.spring.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel
@Entity(name = "RecipePart")
@Table(name = "recipe_part")
public class RecipePart implements Serializable {

    @EmbeddedId
    private Key key;

    @ApiModelProperty(value = "Linked recipe.", example = "Apfelkompott")
    @ManyToOne
    @JoinColumn(name = "recipe_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Recipe recipe;

    @ApiModelProperty(value = "Linked product.", example = "Apfel")
    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name", insertable = false, updatable = false)
    private Product product;

    @ApiModelProperty(value = "Number of the product in the recipe.", example = "2")
    @Column(name = "number", nullable = false)
    private Integer number;

    //==================================================================================================================

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel
    @Embeddable
    public static class Key implements Serializable {

        @ApiModelProperty(value = "Linked recipe name.", example = "Apfelkompott")
        @Column(name = "recipe_name", nullable = false)
        private String recipeName;

        @ApiModelProperty(value = "Linked product name.", example = "Apfel")
        @Column(name = "product_name", nullable = false)
        private String productName;
    }
}
