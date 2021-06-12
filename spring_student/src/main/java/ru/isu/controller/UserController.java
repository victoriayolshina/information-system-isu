package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.isu.model.AutoUser;
import ru.isu.repository.AutoUserRepository;

@Controller
@RequestMapping("/forme")
public class UserController {
//    @Autowired
//    AutoUserRepository autoUserRepository;
//
//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String userInfo(Model model, Authentication authentication){
//
//        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//        AutoUser user = autoUserRepository.findAutoUserByUsername(token.getName());
//
//        model.addAttribute("user",user);
//        return "userinfo";
//    }
}
