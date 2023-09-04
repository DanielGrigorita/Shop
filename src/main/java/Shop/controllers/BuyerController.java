package Shop.controllers;

import Shop.models.Buyer;
import Shop.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BuyerController {

    @Autowired
    private BuyerService buyerService;


    @GetMapping("/buyers")
    public ModelAndView buyer() {
        return new ModelAndView("buyers");
    }

    @PostMapping("/buyersAdd")
    public ModelAndView addBuyer(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("age") int age,
                                 @RequestParam("city") String city,
                                 @RequestParam("street") String street,
                                 @RequestParam("postalCode") int postalCode){
        ModelAndView modelAndView = new ModelAndView("buyers");
        buyerService.addBuyer(firstName, lastName, age, city, street, postalCode);
        modelAndView.addObject("message", "Cumpărătorul a fost adăugat cu succes!");
        return modelAndView;
    }

    @GetMapping("/allBuyerForDelete")
    public ModelAndView getAllBuyersForDelete() {
        List<Buyer> buyers = buyerService.findAllBuyers();
        ModelAndView modelAndView = new ModelAndView("buyers");
        modelAndView.addObject("buyers", buyers);
        return modelAndView;
    }

    @PostMapping("/deleteBuyer")
    public ModelAndView deleteBuyer(@RequestParam(value = "buyerId", required = false) int buyerId) {

        if (buyerId != 0) {
            buyerService.deleteBuyer(buyerId);
        }

        ModelAndView modelAndView = new ModelAndView("buyers");
        modelAndView.addObject("messageDelete", "Cumparatorul a fost șters cu succes!");

        return modelAndView;
    }

    @GetMapping("/allBuyersForUpdate")
    public ModelAndView getAllBuyersForUpdate() {
        List<Buyer> buyers = buyerService.findAllBuyers();
        ModelAndView modelAndView = new ModelAndView("buyers");
        modelAndView.addObject("buyersUpdate", buyers);
        return modelAndView;
    }

    @PostMapping("/updateBuyer")
    public ModelAndView updateBuyers(@RequestParam("updateId") int updateId,
                                     @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("age") int age,
                                     @RequestParam("city") String city,
                                     @RequestParam("street") String street,
                                     @RequestParam("postalCode") int postalCode) {
        ModelAndView modelAndView = new ModelAndView("buyers");
        buyerService.updateBuyer(updateId, firstName, lastName, age, city, street, postalCode );
        modelAndView.addObject("messageUpdate", "Cumparatorul a fost actualizat cu succes!");
        return modelAndView;
    }






}
