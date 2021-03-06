package com.shakag.mapper;

import com.shakag.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Shakag
 * @since 2021-06-10
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    public int saveList(List<SysUser> list);
}
