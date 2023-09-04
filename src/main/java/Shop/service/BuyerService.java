package Shop.service;

import Shop.models.Buyer;
import Shop.models.BuyerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    public List<Buyer> findAllBuyers() {
        return (List<Buyer>) buyerDao.findAll();
    }

    public void addBuyer(String firstName, String lastName, int age, String city, String street, int postalCode) {
        Buyer buyer = new Buyer();
        buyer.setFirstName(firstName);
        buyer.setLastName(lastName);
        buyer.setAge(age);
        buyer.setCity(city);
        buyer.setStreet(street);
        buyer.setPostalCode(postalCode);
        buyerDao.save(buyer);
    }

    public void deleteBuyer(int buyerId) {
        buyerDao.deleteById(buyerId);
    }

    public void updateBuyer(int id, String firstName,String lastName, int age, String city, String street,int postalCode) {
        Optional<Buyer> optionalBuyer = buyerDao.findById(id);
        if (optionalBuyer.isPresent()) {
            Buyer buyer = optionalBuyer.get();
            buyer.setFirstName(firstName);
            buyer.setLastName(lastName);
            buyer.setAge(age);
            buyer.setCity(city);
            buyer.setStreet(street);
            buyer.setPostalCode(postalCode);
            buyerDao.save(buyer);
        }
    }


}
