package com.mackittipat.prg.controller;

import com.mackittipat.prg.CookieUtil;
import com.mackittipat.prg.model.UserModel;
import com.mackittipat.prg.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserValidator userValidator;

    @RequestMapping("showlist.html")
    public String showList(Model model,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           @CookieValue(required = false) String message) {

        if(message != null) {
            model.addAttribute("message", message);
            CookieUtil.deleteCookie(request, response, "message");
        }


        return "showlist";
    }

    @RequestMapping("create.html")
    public String create(@ModelAttribute UserModel userModel) {
        return "create";
    }

    @RequestMapping(value = "save.html", method = RequestMethod.POST)
    public String savePost(HttpServletResponse response,
                           @ModelAttribute UserModel userModel,
                           BindingResult result) {
        userValidator.validate(userModel, result);

        if(!result.hasErrors()) {
            CookieUtil.createCookie(response, "message", "Create user successful");
            return "redirect:showlist.html";
        }

        return "create";
    }

    @RequestMapping(value = "save.html", method = RequestMethod.GET)
    public String saveGet() {
        return "redirect:create.html";
    }

}
