package com.ik.Excel;

import com.alibaba.excel.EasyExcel;

/**
 * TODO
 *
 * @className: ExcelRead
 * @author: weishihuan
 * @date: 2022-07-09 14:35
 **/
public class ExcelRead {
    public static void main(String[] args) {
        String fileName="D:\\user.xlsx";
        EasyExcel.read(fileName,User.class,new ExcelListener()).sheet().doRead();
    }
}
