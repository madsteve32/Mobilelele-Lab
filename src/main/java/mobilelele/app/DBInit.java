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

import java.time.Instant;
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

//        Brand fordBrand = new Brand();
//        fordBrand.setName("Ford");
//        setCurrentTimestamps(fordBrand);
//
//        Brand hondaBrand = new Brand();
//        hondaBrand.setName("Honda");
//        setCurrentTimestamps(hondaBrand);
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
        setCurrentTimestamps(adminRole);

        UserRole userRole = new UserRole();
        userRole.setRole(Role.User);
        setCurrentTimestamps(userRole);

        userRoleRepository.saveAll(List.of(adminRole, userRole));

        User admin = new User();

        admin.setFirstName("Steve");
        admin.setLastName("Lyutov");
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setUserRoles(List.of(adminRole, userRole));
        setCurrentTimestamps(admin);

        User kosi = new User();

        kosi.setFirstName("Kostadinka");
        kosi.setLastName("Dimitrova");
        kosi.setUsername("kosi");
        kosi.setPassword(passwordEncoder.encode("123456"));
        kosi.setUserRoles(List.of(userRole));
        setCurrentTimestamps(kosi);

        userRepository.saveAll(List.of(admin, kosi));
    }

    private void createFiestaOffer(Model modelEntity) {
        Offer fiestaOffer = new Offer();

        fiestaOffer.setEngine(Engine.GASOLINE);
        fiestaOffer.setImageUrl("https://media.autoexpress.co.uk/image/private/s--7btEt2wi--/v1562244788/autoexpress/2017/07/dsc_1328-1.jpg");
        fiestaOffer.setMileage(40000);
        fiestaOffer.setPrice(10000);
        fiestaOffer.setYear(2019);
        fiestaOffer.setText("Karana e ot nemska baba. Zimata v garaj.");
        fiestaOffer.setTransmission(Transmission.MANUAL);
        fiestaOffer.setModel(modelEntity);
        setCurrentTimestamps(fiestaOffer);

        offerRepository.save(fiestaOffer);
    }

    private void createNC750SOffer(Model model) {
        Offer nc750sOffer = new Offer();

        nc750sOffer.setEngine(Engine.GASOLINE);
        nc750sOffer.setImageUrl("https://bikez.com/pictures/large.php?image=../pictures/honda/2018/49239_0_1_4_nc750s_Image%20credits%20-%20Honda.jpg");
        nc750sOffer.setMileage(1500);
        nc750sOffer.setPrice(18500);
        nc750sOffer.setYear(2018);
        nc750sOffer.setText("Close to new always keep it in garage.");
        nc750sOffer.setTransmission(Transmission.MANUAL);
        nc750sOffer.setModel(model);
        setCurrentTimestamps(nc750sOffer);

        offerRepository.save(nc750sOffer);
    }

    private Model initNC750S(Brand hondaBrand) {
        Model nc750s = new Model();

        nc750s.setName("NC750S");
        nc750s.setCategory(Category.Motorcycle);
        nc750s.setImageUrl("https://www.mitchellsmc.co.uk/wp-content/uploads/2020/07/IMG_0686.jpg");
        nc750s.setStartYear(2014);
        nc750s.setBrand(hondaBrand);
        setCurrentTimestamps(nc750s);

        return modelRepository.save(nc750s);
    }

    private Model initFiesta(Brand fordBrand) {
        Model fiesta = new Model();

        fiesta.setName("Fiesta");
        fiesta.setCategory(Category.Car);
        fiesta.setImageUrl("https://s1.cdn.autoevolution.com/images/gallery/FORDFiesta5Doors-2792_1.jpg");
        fiesta.setStartYear(1989);
        fiesta.setBrand(fordBrand);
        setCurrentTimestamps(fiesta);

        return modelRepository.save(fiesta);
    }

    private static void setCurrentTimestamps(BaseEntity baseEntity) {
        baseEntity.setCreated(Instant.now());
        baseEntity.setUpdated(Instant.now());
    }
}
