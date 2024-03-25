package com.example.deliverysystem.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Orders {
    @TableId("order_id")
    private int orderNumber;
    @TableField("name")
    private String productName;
    @TableField("source")
    private String purchaseLocation;
    @TableField("destination")
    private String deliveryLocation;
    @TableField("states")
    private int orderStatus;
    //0代表未接单，1代表进行中 2 代表已完成  3 代表需要输入取件码
    @TableField("student_id")
    private int studentId;//接单人
    @TableField("levels")
    private String levels;
    @TableField("comments")
    private String comments;
    @TableField("deadline")
    private Date deadline;
    @TableField("deliverydate")
    private Date deliverydate;
    @TableField("payment")
    private double payment;
    @TableField("note")
    private String note;
    @TableField("deliver_id")
    private int deliverId;//发单人
    @TableField("isPaid")
    private int isPaid; // 0 代表未付款   1 代表已付款
    @TableField("pickupCode")
    private int pickupCode;

}
