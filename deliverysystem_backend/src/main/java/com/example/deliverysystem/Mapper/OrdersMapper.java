package com.example.deliverysystem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deliverysystem.Entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
