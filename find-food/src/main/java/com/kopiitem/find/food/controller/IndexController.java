/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kopiitem.find.food.controller;

import com.kopiitem.find.food.domain.FoodRequest;
import com.kopiitem.find.food.domain.FoodResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author donny.fm
 */
@Controller
public class IndexController {

    @MessageMapping("/find-food")
    @SendTo("/topic/food")
    public FoodResponse findFood(FoodRequest fReg) {
        FoodResponse fRes = new FoodResponse();
        fRes.setUserName(fReg.getUserName());
        fRes.setResponse(fReg.getFoodName());
        return fRes;
    }
}
