package Shop.controllers;

import Shop.models.Product;
import Shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ModelAndView product() {
        return new ModelAndView("products");
    }

    @PostMapping("/productsAdd")
    public ModelAndView addProduct(@RequestParam("name") String name,
                                   @RequestParam("brand") String brand,
                                   @RequestParam("price") double price,
                                   @RequestParam("quantity") int quantity) {
        ModelAndView modelAndView = new ModelAndView("products");
        productService.addProduct(name, brand, price, quantity);
        modelAndView.addObject("message", "Produsul a fost adăugat cu succes!");
        return modelAndView;
    }

    @GetMapping("/allForDelete")
    public ModelAndView getAllProductsForDelete() {
        List<Product> products = productService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteProductModel(@RequestParam("deleteId") int deleteId) {
        if (deleteId != 0) {
            productService.deleteProduct(deleteId);
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/allForDelete");
        modelAndView.addObject("messageDelete", "Produsul a fost șters cu succes!");
        return modelAndView;
    }



    @GetMapping("/allForUpdate")
    public ModelAndView getAllProductsForUpdate() {
        List<Product> products = productService.findAllProducts();
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("productsUpdate", products);
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView updateProduct(@RequestParam("updateId") int updateId,
                                      @RequestParam("name") String name,
                                      @RequestParam("brand") String brand,
                                      @RequestParam("price") double price,
                                      @RequestParam("quantity") int quantity) {
        ModelAndView modelAndView = new ModelAndView("products");
        productService.updateProduct( updateId, name, brand, price, quantity);
        modelAndView.addObject("messageUpdate", "Produsul a fost actualizat cu succes!");
        return modelAndView;
    }

}
