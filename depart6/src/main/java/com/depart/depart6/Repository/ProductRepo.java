package com.depart.depart6.Repository;

import com.depart.depart6.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {


    Product findByName(String name);
}
