package com.shakag.service.impl;

import com.shakag.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async("multiThread")  // 指定使用beanName为 multiThread的线程池
public class AsyncServiceImpl implements AsyncService {

    public void async(int i){
            System.out.println(Thread.currentThread().getName() + ":" +i);
    }
}
