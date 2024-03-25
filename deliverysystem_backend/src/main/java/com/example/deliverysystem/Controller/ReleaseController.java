package com.example.deliverysystem.Controller;

import com.example.deliverysystem.Entity.Orders;
import com.example.deliverysystem.Service.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReleaseController {
    @Autowired
    ReleaseService releaseService;
    @PostMapping("/releaseorder")
    public ResponseEntity<String> release(@RequestBody Orders order)
    {
        releaseService.release(order);
        return ResponseEntity.ok().build();
    }
}
