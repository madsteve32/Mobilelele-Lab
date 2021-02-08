package mobilelele.app.services.impl;

import mobilelele.app.models.entities.Brand;
import mobilelele.app.models.entities.Model;
import mobilelele.app.models.view.BrandViewModel;
import mobilelele.app.models.view.ModelViewModel;
import mobilelele.app.repositories.ModelRepository;
import mobilelele.app.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {

        List<BrandViewModel> result = new ArrayList<>(); //  <-- final result here

        List<Model> allModels = modelRepository.findAll();

        allModels.forEach(m -> {
            Brand brand = m.getBrand();

            Optional<BrandViewModel> brandViewModelOpt = findByName(result, brand.getName());

            if (brandViewModelOpt.isEmpty()) {

                BrandViewModel newBrandViewModel = new BrandViewModel();
                modelMapper.map(brand, newBrandViewModel);
                result.add(newBrandViewModel);
                brandViewModelOpt = Optional.of(newBrandViewModel);
            }

            ModelViewModel newModelViewModel = new ModelViewModel();
            modelMapper.map(m, newModelViewModel);
            brandViewModelOpt.get().addModel(newModelViewModel);
        });

        return result;
    }

    private Optional<BrandViewModel> findByName(List<BrandViewModel> allModels, String name) {
        return allModels.stream().
                filter(m -> m.getName().equals(name)).findAny();
    }
}
