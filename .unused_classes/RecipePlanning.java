package com.spring.model.entity;

import com.spring.model.entity.compositeKey.RecipePlanningKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RecipePlanningKey.class)
@Entity(name = "RecipePlanning")
@Table(name = "recipe_planning")
public class RecipePlanning implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "recipe_name", referencedColumnName = "name", nullable = false)
    private Recipe recipe;

    //==================================================================================================================

}
