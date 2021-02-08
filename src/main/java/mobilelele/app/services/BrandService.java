package mobilelele.app.services;

import mobilelele.app.models.entities.Brand;
import mobilelele.app.models.view.BrandViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BrandService {
    List<BrandViewModel> getAllBrands();
}
