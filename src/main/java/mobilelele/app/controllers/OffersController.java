package mobilelele.app.controllers;

import mobilelele.app.models.view.OfferDetailsViewModel;
import mobilelele.app.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferService offerService;

    @Autowired
    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public String allOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/{id}")
    public String offerDetails(Model model, @PathVariable long id) {
        OfferDetailsViewModel offerDetailsViewModel = offerService
                .getOfferDetails(id)
                .orElseThrow();

        model.addAttribute("offer", offerDetailsViewModel);

        return "details";
    }
}
