package com.example.deliverysystem.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("students")
public class Students {
    @TableId("student_id")
    private int studentId;
    private String email;
    private String password;
    private String gender;
    private String username;
    private Date birthday;
    private Float points;
    @TableField("credit")
    private int credit;
    private int order_id;
    @TableField("phone")
    private Long phoneNumber;
}
