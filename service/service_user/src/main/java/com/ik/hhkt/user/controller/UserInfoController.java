package com.ik.hhkt.user.controller;


import com.ik.hhkt.model.user.UserInfo;
import com.ik.hhkt.user.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wsh
 * @since 2022-07-14
 */
@RestController
@RequestMapping("/admin/user/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userService;

    /**
     * 通过id得到用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取")
    @GetMapping("inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id) {
        return userService.getById(id);
    }
}

