package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String showRollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String rollDiceGuess(@PathVariable int n, Model model) {
        ArrayList<Integer> diceRolls = new ArrayList<>();

        Random rand = new Random();
        int random = rand.nextInt((6 - 1) + 1) + 1;

        diceRolls.add(random);
        model.addAttribute("diceRolls", diceRolls);
        model.addAttribute("userGuess", n);
        return "roll-dice";
    }

}


