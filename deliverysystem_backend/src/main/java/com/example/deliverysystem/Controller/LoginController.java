package com.example.deliverysystem.Controller;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/studentlogin")
    public ResponseEntity<String> checkLogin(@RequestBody Students student)
    {
        if(loginService.logincheck(student.getUsername(), student.getPassword())) {
            return new ResponseEntity<>("Login Success", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Login failed",HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/studentfindId")
    public int findIdByName(@RequestBody Students student)
    {
        return loginService.getId(student.getUsername());
    }

    @GetMapping("/studentfindPoints/{username}")
    public float getPoints(@PathVariable("username") String username){
        return loginService.getPoints(username);
    }

    @GetMapping("/studentfindCredits/{username}")
    public int getCredits(@PathVariable("username") String username){
        return loginService.getCredits(username);
    }
}
