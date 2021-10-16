package com.shakag.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelListener<T> extends AnalysisEventListener<T> {
    List<T> list = new ArrayList<T>();

    /**
     * 一行一行处理数据
     * @param data 每行数据
     * @param analysisContext 上下文
     */
    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
