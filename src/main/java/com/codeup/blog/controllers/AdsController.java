package com.codeup.blog.controllers;

import com.codeup.blog.models.Ad;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AdsController {

    @GetMapping("/ads")
//    @RequestMapping(value = "/ads", method = RequestMethod.GET)
    public String index(Model model){
        ArrayList<Ad> adsList = new ArrayList<>();
        adsList.add(new Ad("PS1", "Used"));
        adsList.add(new Ad("PS2", "Used"));
        adsList.add(new Ad("PS4", "Used"));
        adsList.add(new Ad("SNES", "Used"));
        model.addAttribute("noAdsFound", adsList.size() == 0);
        model.addAttribute("ads", adsList);
        return "/ads/index";
    }

    @GetMapping("/ads/{id}")
    @ResponseBody
    public String show(@PathVariable long id, Model model){
        model.addAttribute("adId", id);
        model.addAttribute("ad", new Ad("PS1", "Used"));
        return "/ads/show";
    }

    @GetMapping("/ads/create")
    @ResponseBody
    public String showForm(){
        return "view the form for creating an ad";
    }

    @PostMapping("/ads/create")
    @ResponseBody
    public String save(){
        return "create a new ad";
    }
}
