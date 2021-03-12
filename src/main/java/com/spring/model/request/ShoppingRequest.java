package com.spring.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class ShoppingRequest {

    @ApiModelProperty(value = "Shopping list name.", example = "Standardliste")
    private String listName;
    @ApiModelProperty(value = "Product link.", example = "Apfel")
    private String productName;
    @ApiModelProperty(value = "Number of the amount in the stock.", example = "2")
    private int number;
}
