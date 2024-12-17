package com.example.miniprojet.dao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.miniprojet.dao.entitys.Register;

public interface RegisterRepository extends JpaRepository<Register , Long> {

    List<Register> findByIdevent(Long idevent);
    Page<Register> findByIdevent(Long idevent , Pageable pegeable);
    List<Register> findByEmail(String email);
    
}
