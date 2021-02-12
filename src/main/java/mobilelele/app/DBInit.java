package mobilelele.app;

import mobilelele.app.models.entities.*;
import mobilelele.app.models.entities.enums.Category;
import mobilelele.app.models.entities.enums.Engine;
import mobilelele.app.models.entities.enums.Role;
import mobilelele.app.models.entities.enums.Transmission;
import mobilelele.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBInit(ModelRepository modelRepository, BrandRepository brandRepository, OfferRepository offerRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

//        Brand suzukiBrand = new Brand();
//        suzukiBrand.setName("Suzuki");
//
//        brandRepository.save(suzukiBrand);
//
//        Model suzukiGSXR = initGSXR(suzukiBrand);

//        Brand fordBrand = new Brand();
//        fordBrand.setName("Ford");
//
//        Brand hondaBrand = new Brand();
//        hondaBrand.setName("Honda");
//
//        brandRepository.saveAll(List.of(fordBrand, hondaBrand));
//
//        Model fiestaModel = initFiesta(fordBrand);
//        Model nc750sModel = initNC750S(hondaBrand);
//        createFiestaOffer(fiestaModel);
//        createNC750SOffer(nc750sModel);
//
//        initUser();
    }

    private void initUser() {
        UserRole adminRole = new UserRole();
        adminRole.setRole(Role.Admin);

        UserRole userRole = new UserRole();
        userRole.setRole(Role.User);

        userRoleRepository.saveAll(List.of(adminRole, userRole));

        User admin = new User();

        admin.setFirstName("Steve");
        admin.setLastName("Lyutov");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setUserRoles(List.of(adminRole, userRole));

        User kosi = new User();

        kosi.setFirstName("Kostadinka");
        kosi.setLastName("Dimitrova");
        kosi.setUsername("kosi");
        kosi.setPassword(passwordEncoder.encode("123456"));
        kosi.setUserRoles(List.of(userRole));

        userRepository.saveAll(List.of(admin, kosi));
    }

    private Model initGSXR(Brand suzukiBrand) {
        Model gsxr = new Model();

        gsxr.setName("GSXR");
        gsxr.setCategory(Category.Motorcycle);
        gsxr.setImageUrl("https://i.pinimg.com/originals/c3/52/e0/c352e013199702b3ab7ce083aab7227d.jpg");
        gsxr.setStartYear(2006);
        gsxr.setBrand(suzukiBrand);

        return modelRepository.save(gsxr);
    }



    private Model initNC750S(Brand hondaBrand) {
        Model nc750s = new Model();

        nc750s.setName("NC750S");
        nc750s.setCategory(Category.Motorcycle);
        nc750s.setImageUrl("https://www.mitchellsmc.co.uk/wp-content/uploads/2020/07/IMG_0686.jpg");
        nc750s.setStartYear(2014);
        nc750s.setBrand(hondaBrand);

        return modelRepository.save(nc750s);
    }

    private Model initFiesta(Brand fordBrand) {
        Model fiesta = new Model();

        fiesta.setName("Fiesta");
        fiesta.setCategory(Category.Car);
        fiesta.setImageUrl("https://s1.cdn.autoevolution.com/images/gallery/FORDFiesta5Doors-2792_1.jpg");
        fiesta.setStartYear(1989);
        fiesta.setBrand(fordBrand);

        return modelRepository.save(fiesta);
    }
}
