package com.shakag.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shakag.entity.SysUser;
import com.shakag.mapper.IPageMapper;
import com.shakag.service.IPageService;
import org.springframework.stereotype.Service;

@Service
public class IPageServiceImpl extends ServiceImpl<IPageMapper, SysUser> implements IPageService {
    @Override
    public IPage<SysUser> query(IPage<SysUser> page) {
        return this.page(page);
    }
}
