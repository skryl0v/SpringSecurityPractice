package com.skrylov.springsecuritypractice.controller;

import com.skrylov.springsecuritypractice.model.User;
import com.skrylov.springsecuritypractice.service.SecurityService;
import com.skrylov.springsecuritypractice.service.UserService;
import com.skrylov.springsecuritypractice.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm",new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model){
        userValidator.validate(user,bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.save(user);
        securityService.autoLogin(user.getUsername(), user.getConfirmPassword());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model,String error, String logout){
        if(error != null){
            model.addAttribute("error","Username or password is incorrect");
        }

        if(logout != null){
            model.addAttribute("message","Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = {"/","welcome"},method = RequestMethod.GET)
    public String welcome(Model model){
        return "welcome";
    }

    @RequestMapping(value = {"admin"},method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }

}
