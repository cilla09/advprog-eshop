package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if (product.getProductId() == null){
            product.setProductId(String.valueOf(UUID.randomUUID()));
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public void delete(String id) {
        Product productToDelete = findById(id);
        productData.remove(productToDelete);
    }

    public Product edit(String productId, Product product) {
        Product productToEdit = findById(productId);
        if (productToEdit != null) {
            productToEdit.setProductName(product.getProductName());
            productToEdit.setProductQuantity(product.getProductQuantity());
        }
        return productToEdit;
    }
}
