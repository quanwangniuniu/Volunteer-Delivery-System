package com.example.deliverysystem.Controller;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.registry.Registry;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @PostMapping("/userregister")
    public void register(@RequestBody Students students)
    {
        System.out.println(students);
        registerService.register(students);
    }
}
