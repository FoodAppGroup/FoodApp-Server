package com.spring.model.composite;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StockKey implements Serializable {

    @ApiModelProperty(value = "Link to a product.", example = "Apfel")
    private String productName;
}
