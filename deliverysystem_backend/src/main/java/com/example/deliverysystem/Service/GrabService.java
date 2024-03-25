package com.example.deliverysystem.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.deliverysystem.Entity.Orders;
import com.example.deliverysystem.Mapper.OrdersMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GrabService {
    @Autowired
    OrdersMapper ordersMapper;
    public List<Orders> getAllorders()
    {
        return ordersMapper.selectList(null);
    }

    public void grabOrders(Orders order){
         ordersMapper.updateById(order);
    }

    public List<Orders> findAllordersById(int id){
        Map<String, Object> map = new HashMap<>();
        map.put("deliver_id",id);
        return ordersMapper.selectByMap(map);
    }

    public List<Orders> findAllmyOrdersById(int id)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("student_id",id);
        return ordersMapper.selectByMap(map);
    }

    public Orders getThisOrder(int id) {
        return ordersMapper.selectById(id);
    }
}
