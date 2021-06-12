package ru.isu.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.isu.model.AutoUser;
import ru.isu.model.Student;
import ru.isu.repository.AutoUserRepository;
import ru.isu.repository.StudentRepository;

@Controller
public class MainController {

    public String gant(){
        return "gant";
    }

    @Autowired
    AutoUserRepository autoUserRepository;

    //Первичная страница
    @RequestMapping(value = "/")
    public String home() {
        System.out.println("home");
        return "home";
    }

    @RequestMapping(value = "/")
    public String home(Authentication authentication) {
        AutoUser autoUser = autoUserRepository.findAutoUserByUsername(authentication.getName());
        switch (autoUser.getRole()){
            case ("ROLE_STUDENT"):
                return "redirect:/student/{studentId}";
            case ("ROLE_CURATOR"):
                return "redirect:/curator/{curatorId}";
            case ("ROLE_DEANSOFFICE"):
                return "redirect:/deansoffice/{deansofficeId}";
        }
        System.out.println("home");
        return "home";
    }

//    //Первичная страница
//    @RequestMapping(value = "/home")
//    public String home(Authentication authentication) {
//        System.out.println(authentication.isAuthenticated());
//        System.out.println("home");
//        return "home";
//    }

    //Переход на авторизацию
    @RequestMapping(value = "/login")
    public String login(Model model){
        System.out.println("Login");
        return "login";
    }

    //Переход на логирование
    @RequestMapping(value = "/register", method= RequestMethod.GET)
    public String goRegister(){
        return "register";
    }

    //После регистирования с сохранением информации о пользовании
    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public String register(@ModelAttribute AutoUser user){
        System.out.println(user);
        autoUserRepository.save(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "home";
    }


}
