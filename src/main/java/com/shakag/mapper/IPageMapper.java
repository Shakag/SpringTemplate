package com.shakag.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shakag.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IPageMapper extends BaseMapper<SysUser> {
}
