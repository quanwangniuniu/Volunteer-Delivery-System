package com.example.deliverysystem.Service;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    StudentsMapper studentsMapper;
    public void register(Students student)
    {
        studentsMapper.insert(student);
    }
}
