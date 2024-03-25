package com.example.deliverysystem.Service;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
    @Autowired
    StudentsMapper studentsMapper;
    public void payOrder(Students student){
        studentsMapper.updateById(student);
    }
    public Students findStudentById(int id) {
        return studentsMapper.selectById(id);
    }
}
