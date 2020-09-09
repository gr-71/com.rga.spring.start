package com.rga.homework2.repository;

import org.springframework.stereotype.Repository;
import com.rga.homework2.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepoImpl {

    private Map<Long, Product> repo = new HashMap<>();
    private long ind = 0;

    {
        repo.put(++ind, new Product(ind, "Cheese", 635.5));
        repo.put(++ind, new Product(ind, "Milk", 66.0));
        repo.put(++ind, new Product(ind, "Chocolate", 96.0));
        repo.put(++ind, new Product(ind, "Bread", 39.0));
        repo.put(++ind, new Product(ind, "Beer", 88.0));
    }

    public Product getById(Long id){
        return repo.get(id);
    }

    public List<Product> getAll(){
        return new ArrayList<>(repo.values());
    }

    public Product save(Product product){
        if(product.getId() == null){
            product.setId(++ind);
        }
        repo.put(product.getId(), product);
        return product;
    }

// Добавил удаление продукта по ID :
    public void deleteById (Long id){
        for (int i = 0; i < repo.size(); i++) {
            if (repo.get(i).getId().equals(id)){
                repo.remove(i);
                return;
            }
        }
    }
}
