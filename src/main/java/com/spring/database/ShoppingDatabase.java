package com.spring.database;

import com.spring.database.repository.ShoppingRepository;
import com.spring.model.entity.Product;
import com.spring.model.entity.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ShoppingDatabase {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private ProductDatabase productDatabase;


    public Shopping getElement(String listName, String productName) throws IllegalArgumentException {
        Optional<Shopping> shoppingList = shoppingRepository.findById(new Shopping.ShoppingKey(listName, productName));
        if (shoppingList.isPresent()) {
            return shoppingList.get();
        } else {
            throw new IllegalArgumentException("Shopping element not found: '" + productName + "'");
        }
    }

    public List<Shopping> getAllElementsFromList(String listName) {
        return shoppingRepository.findAll().stream().filter(item -> item.getKey().getListName().equalsIgnoreCase(listName)).collect(Collectors.toList());
    }

    public List<Shopping> getAllElements() {
        return shoppingRepository.findAll();
    }

    public Shopping addElement(String listName, String productName, int number) throws IllegalArgumentException {
        Product product = productDatabase.getElement(productName);

        Shopping shoppingElement = new Shopping();
        shoppingElement.setKey(new Shopping.ShoppingKey(listName, productName));
        shoppingElement.setNumber(number);
        shoppingRepository.saveAndFlush(shoppingElement);

        shoppingElement.setProduct(product); //TODO set before saving (creates NullPointer) and return save()
        return shoppingElement;
    }

    public Shopping updateElement(String listName, String productName, int number) throws IllegalArgumentException {
        Shopping shopping = getElement(listName, productName);
        shopping.setNumber(number);
        return shoppingRepository.save(shopping);
    }

    public Shopping removeElement(String listName, String productName) throws IllegalArgumentException {
        Shopping shoppingElement = getElement(listName, productName);
        shoppingRepository.delete(shoppingElement);
        return shoppingElement;
    }
}
