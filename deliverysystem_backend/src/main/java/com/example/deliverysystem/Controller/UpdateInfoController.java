package com.example.deliverysystem.Controller;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Service.UpdateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UpdateInfoController {
    @Autowired
    UpdateInfoService updateInfoService;
    @PostMapping("/updateInfo")
    public void updateInfo(@RequestBody Students student)
    {
        updateInfoService.updateInfo(student);
    }
    @GetMapping ("/getStudentInfo/{id}")
    public Students getStudentInfo(@PathVariable int id)
    {
        return updateInfoService.getStudent(id);
    }
}
