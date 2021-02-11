package mobilelele.app.controllers;

import mobilelele.app.models.entities.enums.Engine;
import mobilelele.app.models.entities.enums.Transmission;
import mobilelele.app.models.service.OfferServiceModel;
import mobilelele.app.models.view.OfferDetailsViewModel;
import mobilelele.app.services.BrandService;
import mobilelele.app.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OfferService offerService;
    private final BrandService brandService;

    @Autowired
    public OffersController(OfferService offerService, BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
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

    @ModelAttribute("offerServiceModel")
    public OfferServiceModel offerServiceModel() {
        return new OfferServiceModel();
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("engines", Engine.values());
        model.addAttribute("transmissions", Transmission.values());

        return "offer-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute OfferServiceModel offerServiceModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerServiceModel", offerServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerServiceModel", bindingResult);

            return "redirect:/offers/add";
        }

        offerService.save(offerServiceModel);

        return "redirect:/offers/all";
    }
}
