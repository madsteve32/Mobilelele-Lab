package mobilelele.app.controllers;

import mobilelele.app.models.service.UserRegisterServiceModel;
import mobilelele.app.models.service.UserServiceModel;
import mobilelele.app.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterServiceModel")) {
            model.addAttribute("userRegisterServiceModel", new UserRegisterServiceModel());
        }

        return "auth-register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute UserRegisterServiceModel userRegisterServiceModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterServiceModel", userRegisterServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterServiceModel", bindingResult);

            return "redirect:/register";
        }



        userService.registerUser(userRegisterServiceModel);

        return "redirect:/login";
    }
}
