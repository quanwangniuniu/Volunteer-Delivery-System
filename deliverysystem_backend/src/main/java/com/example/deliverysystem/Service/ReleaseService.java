package com.example.deliverysystem.Service;

import com.example.deliverysystem.Entity.Orders;
import com.example.deliverysystem.Mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReleaseService {
    @Autowired
    OrdersMapper ordersMapper;
    public void release(Orders order)
    {
        ordersMapper.insert(order);
    }
}
