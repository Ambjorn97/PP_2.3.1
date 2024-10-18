package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String deleteUser() {
        return "delete-user";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = true) int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String updateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-update";
    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user-update";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("single_user", userService.getUserById(id));
        return "user";
    }


}
