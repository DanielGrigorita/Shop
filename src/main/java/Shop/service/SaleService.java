package Shop.service;

import Shop.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    @Autowired
    SaleDao saleDao;
    @Autowired
    private BuyerDao buyerDao;
    @Autowired
    ProductDao productDao;



    public List<Buyer> findAllBuyer(){
        return (List<Buyer>) buyerDao.findAll();
    }

    public List<Product> findAllProduct(){
        return (List<Product>) productDao.findAll();
    }

    public Buyer findBuyerById(int buyerId) {
        return buyerDao.findById(buyerId).orElse(null);
    }

    public Product findProductById(int productId) {
        return productDao.findById(productId).orElse(null);
    }

    public void saveSale(Sale sale) {
        saleDao.save(sale);
    }

    public void sellProduct(Buyer buyer, Product product, int quantity) {
        if (product.getQuantity() >= quantity) {
            Sale sale = new Sale();
            sale.setBuyer(buyer);
            sale.setProduct(product);
            sale.setQuantity(quantity);
            sale.setSalePrice(product.getPrice() * quantity);

            product.setQuantity(product.getQuantity() - quantity);
            buyer.setPurchasedQuantity(buyer.getPurchasedQuantity() + quantity);

            saveSale(sale);

            buyerDao.save(buyer);
            productDao.save(product);
        } else {
            throw new RuntimeException("Not enough quantity available for sale.");
        }
    }
}

