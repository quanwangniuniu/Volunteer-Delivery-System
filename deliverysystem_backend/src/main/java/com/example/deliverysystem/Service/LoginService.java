package com.example.deliverysystem.Service;

import com.example.deliverysystem.Entity.Students;
import com.example.deliverysystem.Mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {
    @Autowired
    StudentsMapper studentsMapper;
    public boolean logincheck(String username,String password)
    {
        return Objects.equals(studentsMapper.getPasswordByuserName(username), password);
    }

    public int getId(String username)
    {
        return studentsMapper.getIdByuserName(username);
    }

    public float getPoints(String username)
    {
        return studentsMapper.getPointByuserName(username);
    }

    public int getCredits(String username) {
        return studentsMapper.getCreditByuserName(username);
    }
}
