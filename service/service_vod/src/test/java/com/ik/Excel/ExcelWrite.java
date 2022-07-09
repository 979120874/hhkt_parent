package com.ik.Excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @className: ExcelDemo
 * @author: weishihuan
 * @date: 2022-07-09 13:49
 **/
public class ExcelWrite {
    public static void main(String[] args) {
        String fileName="D:\\user.xlsx";
        EasyExcel.write(fileName,User.class).sheet("模板").doWrite(doList());
    }
    public static List doList(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("V"+i);
            users.add(user);
        }
        return users;
    }
}
