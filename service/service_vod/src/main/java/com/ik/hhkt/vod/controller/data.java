package com.ik.hhkt.vod.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO
 *
 * @className: data
 * @author: weishihuan
 * @date: 2022-07-07 16:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class data {
    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
}
