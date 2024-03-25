package com.example.deliverysystem.Controller;

import com.example.deliverysystem.Entity.Orders;
import com.example.deliverysystem.Service.GrabService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrabController {
   @Autowired
    GrabService grabService;
   @GetMapping("/getallorders")
   public List<Orders> getallOrders(){
       return grabService.getAllorders();
   }

   @PostMapping("/graborders")
    public void grabOrder(@RequestBody Orders order){
       grabService.grabOrders(order);
   }
   //获取所有当前用户发送的订单
   @GetMapping("/findOrdersById/{id}")
    public List<Orders> findOrderById(@PathVariable("id") int id){
        return grabService.findAllordersById(id);
    }
    //获取所有当前用户接的订单
    @GetMapping("/findMyOrdersById/{id}")
    public List<Orders> findMyOrderById(@PathVariable("id") int id){
        return grabService.findAllmyOrdersById(id);
    }
    // 根据当前订单的id获取当前订单的所有信息
    @GetMapping("/findOrderById/{id}")
    public Orders getThisOrder(@PathVariable("id")int id)
    {
        return grabService.getThisOrder(id);
    }
}
