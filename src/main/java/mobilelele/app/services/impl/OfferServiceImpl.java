package mobilelele.app.services.impl;


import mobilelele.app.models.entities.Offer;
import mobilelele.app.models.service.OfferServiceModel;
import mobilelele.app.models.view.OfferDetailsViewModel;
import mobilelele.app.models.view.OfferSummaryViewModel;
import mobilelele.app.repositories.ModelRepository;
import mobilelele.app.repositories.OfferRepository;
import mobilelele.app.repositories.UserRepository;
import mobilelele.app.security.CurrentUser;
import mobilelele.app.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    private final CurrentUser currentUser;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(CurrentUser currentUser, UserRepository userRepository, ModelRepository modelRepository, OfferRepository offerRepository, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OfferSummaryViewModel> getAllOffers() {
        List<OfferSummaryViewModel> result = new ArrayList<>(); //  <-- final result here

        List<Offer> offers = offerRepository.findAll();

        offers.forEach(offer -> {
            OfferSummaryViewModel offerSummaryViewModel = new OfferSummaryViewModel();
            modelMapper.map(offer, offerSummaryViewModel);
            result.add(offerSummaryViewModel);
        });

        return result;
    }

    @Override
    public Optional<OfferDetailsViewModel> getOfferDetails(long id) {
        return offerRepository.findById(id)
                .map(OfferServiceImpl::mapToDetails);
    }

    @Override
    public long save(OfferServiceModel offerServiceModel) {
        Offer offer = newEntity(offerServiceModel);
        Offer newOffer = offerRepository.save(offer);

        return newOffer.getId();
    }

    private Offer newEntity(OfferServiceModel offerServiceModel){
        Offer offer = new Offer();
        modelMapper.map(offerServiceModel, offer);

        offer.setModel(modelRepository.findById(offerServiceModel.getModelId()).orElseThrow());
        offer.setSeller(userRepository.findByUsername(currentUser.getName()).orElseThrow());
        offer.setCreated(Instant.now());
        offer.setUpdated(Instant.now());

        return offer;
    }

    private static OfferSummaryViewModel mapToSummary(Offer offer) {
        OfferSummaryViewModel offerModel =new OfferSummaryViewModel();
        mapToSummary(offer, offerModel);

        return offerModel;
    }

    private static void mapToSummary(Offer offer, OfferSummaryViewModel offerModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(offer, offerModel);
        offerModel.setModel(offer.getModel().getName());
        offerModel.setBrand(offer.getModel().getBrand().getName());
    }

    private static OfferDetailsViewModel mapToDetails(Offer offer) {
        OfferDetailsViewModel offerModel = new OfferDetailsViewModel();
        mapToSummary(offer, offerModel);

        return offerModel;
    }
}
