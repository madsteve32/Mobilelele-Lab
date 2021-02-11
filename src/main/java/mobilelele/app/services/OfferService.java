package mobilelele.app.services;

import mobilelele.app.models.service.OfferServiceModel;
import mobilelele.app.models.view.OfferDetailsViewModel;
import mobilelele.app.models.view.OfferSummaryViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface OfferService {

    List<OfferSummaryViewModel> getAllOffers();

    Optional<OfferDetailsViewModel> getOfferDetails(long id);

    long save(OfferServiceModel offerServiceModel);
}
