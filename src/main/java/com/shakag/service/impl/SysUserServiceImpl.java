package com.shakag.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shakag.entity.SysUser;
import com.shakag.mapper.SysUserMapper;
import com.shakag.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Shakag
 * @since 2021-06-10
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getSysUserByName(String name) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",name);
        return this.getOne(wrapper);
    }
}
