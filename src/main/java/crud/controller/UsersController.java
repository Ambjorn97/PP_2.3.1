package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/add")
    public String addUserForm() {
        return "add-user";
    }
    @PostMapping("/add")
    public String addUser(@RequestParam(value = "name", required = true) String name, @RequestParam(value = "surname", required = true) String surname) {
        userService.addUser(new User(name, surname));
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
    public String updateUser(@ModelAttribute("user") User user) {
        User exUser = userService.getUserById(user.getId());
        exUser.setName(user.getName());
        exUser.setSurname(user.getSurname());
        userService.updateUser(exUser);
        return "redirect:/users";
    }
    @GetMapping("/user")
    public String showUser(@RequestParam("id")int id, Model model) {
        model.addAttribute("single_user", userService.getUserById(id));
        return "user";
    }


}
