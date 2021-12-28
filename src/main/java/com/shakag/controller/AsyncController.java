package com.shakag.controller;

import com.shakag.service.AsyncService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AsyncController {
    private final AsyncService asyncService;

    @GetMapping("/multiThread")
    public void thread() {
        for (int i = 0; i < 100; i++) {
            asyncService.async(i);
        }
    }
}
