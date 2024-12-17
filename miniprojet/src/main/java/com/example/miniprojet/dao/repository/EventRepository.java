package com.example.miniprojet.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.miniprojet.dao.entitys.Event;

public interface EventRepository extends JpaRepository<Event , Long> {

    

} 
