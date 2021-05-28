package com.revature.p1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.p1.daos.AccountDAO;
import com.revature.p1.models.account.Account;
import com.revature.p1.services.AccountOpeningService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class _AccountController {

    private AccountOpeningService accountOpeningService = new AccountOpeningService(new AccountDAO());

    public void createAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("in create account account controller");

//        user_id, type_id, acct_name
//        String user_id = req.getParameter("user_id");
//        String type_id = req.getParameter("type_id");
//        String acct_name = req.getParameter("acct_name");

        //get req in here then convert accordingly

        Map<String, Object> jsonMap = new ObjectMapper().readValue(req.getInputStream(), HashMap.class);
        System.out.println(jsonMap);


//        Account acct = new Account(jsonMap.get("user_id")., jsonMap.get("type_id"), jsonMap.get("acct_name"));
//        accountOpeningService.createAccount(Account acct);


    }

}
