package com.shakag.controller;

import com.alibaba.excel.EasyExcel;
import com.shakag.common.Result;
import com.shakag.entity.Excel;
import com.shakag.listener.ExcelListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class FileLoadController {

    @PostMapping("/upload/file")
    public Result upload(@RequestParam("upload_file") MultipartFile multipartFile){
//        byte[] bytes = multipartFile.getBytes();
        if(multipartFile.isEmpty()){
            return Result.fail("上传失败");
        }
        String filename = multipartFile.getOriginalFilename();
        File file = new File("C:\\Users\\Administrator\\Desktop\\" + filename);
        try {
            //将获取到的文件 存储在指定目录
            multipartFile.transferTo(file);
            return Result.success("上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail("上传失败");
    }

    @PostMapping("/download/file")
    public void download(HttpServletResponse response) throws IOException {
        //设置返回值的类型，名称
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition","attachment;fileName=1.txt");

        //获得返回流
        OutputStream os = response.getOutputStream();

        //获得原文件流
        FileInputStream fis = new FileInputStream("src/main/resources/txt/1.txt");
        BufferedInputStream bis = new BufferedInputStream(fis); //缓冲流提升处理速度

        //写入流
        byte[] buffer = new byte[1024];
        int read = fis.read(buffer);
        while(read != -1){
            os.write(buffer,0,read);
            read = fis.read(buffer);
        }

        bis.close();
    }

    @PostMapping("/upload/excel")
    public Result uploadExcel(@RequestParam MultipartFile file) throws IOException{
        ExcelListener<Excel> listener = new ExcelListener<>();

        //读取第一个sheet 文件流会自动关闭, headRowNumber(2)指定表头在第几行
        EasyExcel.read(file.getInputStream(), Excel.class, listener).sheet().headRowNumber(2).doRead();

        //获取到的表格数据 list
        List<Excel> list = listener.getList();

        return Result.success();
    }
}
