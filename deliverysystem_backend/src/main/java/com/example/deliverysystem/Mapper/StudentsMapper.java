package com.example.deliverysystem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.deliverysystem.Entity.Students;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentsMapper extends BaseMapper<Students> {
    @Select("SELECT password from students where username=#{username}")
    String getPasswordByuserName(@Param("username")String username);

    @Select("SELECT student_id from students where username=#{username}")
    int getIdByuserName(@Param("username")String username);

    @Select("SELECT points from students where username=#{username}")
    int getPointByuserName(@Param("username")String username);
    @Select("SELECT credit from students where username=#{username}")
    int getCreditByuserName(String username);
}
