package web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/people")
@Transactional
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "people/getAllUsers";
    }


    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "people/getUserById";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "people/newUser";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String removeUserById(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String updateUserById(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUserById(id, user);
        return "redirect:/people";
    }
}
