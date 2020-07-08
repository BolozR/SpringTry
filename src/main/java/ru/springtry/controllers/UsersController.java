package ru.springtry.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.springtry.dao.UsersDao;
import ru.springtry.forms.UserForm;
import ru.springtry.models.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UsersDao usersDao;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers(@RequestParam(value = "first_name", required = false) String frist_name){
        List<User> users = null;
        if(frist_name != null) {
            users = usersDao.findAllByFirstName(frist_name);
        } else {
            users = usersDao.findAll();
        }
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("usersFromServer", users);
        return modelAndView;
    }

    @RequestMapping(path = "/users/{user-id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("user-id") Long userId){
        Optional<User> userCandidate = usersDao.find(userId);
        ModelAndView modelAndView = new ModelAndView("users");
        userCandidate.ifPresent(user -> modelAndView.addObject("usersFromServer", Collections.singletonList(user)));
        return modelAndView;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String addUser(UserForm userForm){
        User newUser = User.from(userForm);
        usersDao.save(newUser);
        return "redirect:users";
    }
}
