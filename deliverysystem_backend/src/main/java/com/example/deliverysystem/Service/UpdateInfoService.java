package com.example.deliverysystem.Service;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateInfoService {
    @Autowired
    StudentsMapper studentsMapper;
    public void updateInfo(Students student)
    {
        studentsMapper.updateById(student);
    }

    public Students getStudent(int id){
        return studentsMapper.selectById(id);
    }
}
