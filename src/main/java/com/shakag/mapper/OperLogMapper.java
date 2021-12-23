package com.shakag.mapper;

import com.shakag.entity.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperLogMapper {
    public void save(SysOperLog sysOperLog);
}
