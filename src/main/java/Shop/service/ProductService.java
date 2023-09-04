package Shop.service;

import Shop.models.Product;
import Shop.models.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public void addProduct(String name, String brand, double price, int quantity){

        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setQuantity(quantity);
        productDao.save(product);
    }

    public void deleteProduct(int id){
        productDao.deleteById(id);
    }

    public List<Product> findAllProducts(){
        return  (List<Product>) productDao.findAll();
    }

    public void updateProduct(int id, String name, String brand, double price, int quantity) {
        Optional<Product> optionalProduct = productDao.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(name);
            product.setBrand(brand);
            product.setPrice(price);
            product.setQuantity(quantity);
            productDao.save(product);
        }
    }

}
