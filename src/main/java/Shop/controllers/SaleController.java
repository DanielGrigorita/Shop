package Shop.controllers;

import Shop.models.Buyer;
import Shop.models.Product;
import Shop.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SaleController {

    @Autowired
    SaleService saleService;


    @GetMapping("/sales")
    public ModelAndView view(){
        List<Buyer> buyers = saleService.findAllBuyer();
        ModelAndView modelAndView = new ModelAndView("sales");
        modelAndView.addObject("buyers", buyers);

        List<Product> products = saleService.findAllProduct();
        modelAndView.addObject("products", products);

        return modelAndView;
    }


    @PostMapping("/buy")
    public String createSale(@RequestParam("buyer.id") int buyerId,
                             @RequestParam("product.id") int productId,
                             @RequestParam("quantity") Integer quantity,
                             Model model) {
        try {
            Buyer buyer = saleService.findBuyerById(buyerId);
            Product product = saleService.findProductById(productId);

            if (product.getQuantity() >= quantity) {
                saleService.sellProduct(buyer, product, quantity);
                model.addAttribute("success", "Vânzarea a fost înregistrată cu succes!");
                model.addAttribute("buyerId", buyerId);
                model.addAttribute("productId", productId);
            } else if (product.getQuantity() < quantity && product.getQuantity() > 0 ){
                model.addAttribute("error", "Nu avem decât " + product.getQuantity() + " produse disponibile.");
                model.addAttribute("buyerId", buyerId);
                model.addAttribute("productId", productId);
            } else if (product.getQuantity() == 0) {
                model.addAttribute("error", "Nu avem produse disponibile.");
                model.addAttribute("buyerId", buyerId);
                model.addAttribute("productId", productId);
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", "A apărut o eroare în procesul de înregistrare a vânzării.");
            model.addAttribute("buyerId", buyerId);
            model.addAttribute("productId", productId);
        }

        List<Buyer> buyers = saleService.findAllBuyer();
        model.addAttribute("buyers", buyers);

        List<Product> products = saleService.findAllProduct();
        model.addAttribute("products", products);

        return "sales";
    }




}
