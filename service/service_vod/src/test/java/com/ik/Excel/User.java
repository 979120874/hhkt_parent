package com.ik.Excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @className: User
 * @author: weishihuan
 * @date: 2022-07-09 13:47
 **/
@Data
public class User {
    @ExcelProperty(value = "用户ID",index = 0)
    private Integer id;
    @ExcelProperty(value = "用户姓名",index = 1)
    private String name;
}
