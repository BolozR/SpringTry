package ru.springtry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.springtry.forms.UserForm;
import ru.springtry.models.User;
import ru.springtry.repositories.UsersRepository;

import java.util.List;

@Controller
public class UsersWithJpaController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(path = "/jpa/users", method = RequestMethod.GET)
    public ModelAndView getUsers(){
        List<User> users = usersRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path="/jpa/find_user")
    public ModelAndView findUsers(@RequestParam(value = "first_name", required = false) String frist_name){
        List<User> users = null;
        if(frist_name != null) {
            users = usersRepository.findAllByFirstName(frist_name);
        } else {
            users = usersRepository.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path = "/jpa/users", method = RequestMethod.POST)
    public String saveUsers(UserForm userForm){
        User newUser = User.from(userForm);
        usersRepository.save(newUser);
        return "redirect:/jpa/users";
    }

}
