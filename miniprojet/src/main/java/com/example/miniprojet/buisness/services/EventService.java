package com.example.miniprojet.buisness.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.miniprojet.dao.entitys.Event;

public interface EventService {

    //Read opérations
    List<Event> getAllEvents();
    Event getEventById(Long id) /*throws Exception*/;
    //create
    Event addEvent(Event event);
    //Update
    Event updateEvent(Event event);
    //Delete
    void deleteEventById(Long id);

    Page<Event> getAllEventPagination(Pageable pegeable);
    Page<Event> getAllEventPaginationSortDate(Pageable pageable , String order);

    Page<Event> getAllEventPaginationSortCategorie(Pageable pageable , String order);

    
}
