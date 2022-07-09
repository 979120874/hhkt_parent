package com.ik.Excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * TODO
 *
 * @className: ExcelListener
 * @author: weishihuan
 * @date: 2022-07-09 14:37
 **/
public class ExcelListener extends AnalysisEventListener<User> {
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据:"+user);
    }
    @Override
    public void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context){
        System.out.println(headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成！");
    }
}
