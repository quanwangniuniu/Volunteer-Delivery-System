package com.example.deliverysystem.Controller;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PayController {
    @Autowired
    PayService payService;
    @GetMapping("/findStudent/{id}")
    public Students findStudentById(@PathVariable int id)
    {
        return payService.findStudentById(id);
    }
    @PostMapping("/payOrder")
    public void payOrder(@RequestBody Students student)
    {
        payService.payOrder(student);
    }
}
