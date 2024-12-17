package com.example.miniprojet.buisness.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.miniprojet.dao.entitys.Register;


public interface RegisterService {
    public Register addUser(Register register);
    public List<Register> getallUsersbyid(Long id);
    public void deleteUserById(Long id);
    public Integer getuserbyemail(String email);
    public Page<Register> getAllUserPaginationByid(Pageable pegeable , Long id);
}
