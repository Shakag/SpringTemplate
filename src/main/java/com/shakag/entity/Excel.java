package com.shakag.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;

/**
 * EasyExcel和 @Accessors(chain = true) 注解冲突
 */
@Data
public class Excel implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * ExcelProperty 指定excel文件中表头对应的字段
     */
    @ExcelProperty("姓名")
    private String username;

    /**
     * ExcelProperty 指定excel文件中对应下标的数据
     */
    @ExcelProperty(index = 2)
    private String password;

    /**
     * ExcelIgnore 操作excel文件时忽略此字段
     */
    @ExcelIgnore
    private String avatar;
}
