package com.spring.model.entity;

import com.spring.model.entity.compositeKey.RecipePartKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RecipePartKey.class)
@Entity(name = "RecipePart")
@Table(name = "recipe_part")
public class RecipePart implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_name", referencedColumnName = "name", nullable = false)
    private Recipe recipe;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_name", referencedColumnName = "name", nullable = false)
    private Product product;

    @Column(name = "number", nullable = false)
    private Integer number;

    //==================================================================================================================

}
