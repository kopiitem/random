/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kopiitem.find.food.domain;

/**
 *
 * @author donny.fm
 */
public class FoodResponse {

    private String userName;
    private String response;

    public FoodResponse() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public FoodResponse(String response) {
        this.response = response;
    }

}
