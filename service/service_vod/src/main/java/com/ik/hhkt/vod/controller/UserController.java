package com.ik.hhkt.vod.controller;

import com.ik.hhkt.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * TODO
 *
 * @className: UserController
 * @author: weishihuan
 * @date: 2022-07-07 16:40
 **/
@RestController
@RequestMapping("/admin/vod/user")
@CrossOrigin
public class UserController {

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Result login(){
        Hashtable<String, String> map = new Hashtable<>();
        map.put("token","admin-token");
        return Result.ok(map).code(20000);
    }

    @RequestMapping(value = "info",method = RequestMethod.GET)
    public Result info(@RequestParam("token") String token){
        ArrayList<String> roles = new ArrayList<>();
        roles.add("admin");
        data data = new data(roles, "I am a super administrator", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif", "Super Admin");
        return Result.ok(data).code(20000);
    }

}
