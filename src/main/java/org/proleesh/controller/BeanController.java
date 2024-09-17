package org.proleesh.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {
    @Resource(name="p2")
    private String name;

    @RequestMapping("bean")
    public String showName(){
        return name.toString();
    }
}
