package com.shakag.controller;

import com.alibaba.excel.EasyExcel;
import com.shakag.common.Result;
import com.shakag.entity.Excel;
import com.shakag.listener.ExcelListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
public class Controllers {

    @PostMapping("upload")
    public Result upload(@RequestParam MultipartFile file) throws IOException{
        ExcelListener<Excel> listener = new ExcelListener<>();

        //读取第一个sheet 文件流会自动关闭, headRowNumber(2)指定表头在第几行
        EasyExcel.read(file.getInputStream(), Excel.class, listener).sheet().headRowNumber(2).doRead();

        //获取到的表格数据 list
        List<Excel> list = listener.getList();

        return Result.success();
    }
}
