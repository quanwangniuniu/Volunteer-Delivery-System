package com.example.deliverysystem.Controller;

import com.example.deliverysystem.Entity.Orders;
import com.example.deliverysystem.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/comment")
    public ResponseEntity<String> setcomment(@RequestBody Orders order)
    {
        commentService.setComment(order);
        return new ResponseEntity<>("Comment Success", HttpStatus.OK);
    }
}
