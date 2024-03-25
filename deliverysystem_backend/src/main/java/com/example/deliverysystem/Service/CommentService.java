package com.example.deliverysystem.Service;

import com.example.deliverysystem.Entity.Orders;
import com.example.deliverysystem.Mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    OrdersMapper ordersMapper;
    public void setComment(Orders order)
    {
        ordersMapper.updateById(order);
    }
}
