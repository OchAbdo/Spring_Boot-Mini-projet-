package com.example.miniprojet.buisness.servicesimpl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.miniprojet.buisness.services.EventService;
import com.example.miniprojet.dao.entitys.Event;
import com.example.miniprojet.dao.repository.EventRepository;
import com.example.miniprojet.exception.BadRequestException;
import com.example.miniprojet.exception.EventNotFoundException;



@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository ;
    }

    @Override
    public List<Event> getAllEvents() {
        try {
            return  this.eventRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        
    }

    @Override
    public Event getEventById(Long id) {
        if(id == null){
            return null ;
        }
        return this.eventRepository.findById(id)
        .orElseThrow(()->new EventNotFoundException(id));
    }

    @Override
    public Event addEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public Event updateEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (Exception exception) {
            throw new BadRequestException(exception.getMessage());
        }
    }

    @Override
    public void deleteEventById(Long id) {
        if(id == null){
            return ;
        }else if(this.eventRepository.existsById(id)){
            this.eventRepository.deleteById(id);
        }else{
            throw new EventNotFoundException(id);
        }
    }

    @Override
    public Page<Event> getAllEventPagination(Pageable pegeable) {
        if(pegeable ==null){
            return null;
        }
        return this.eventRepository.findAll(pegeable);
    }

    @Override
    public Page<Event> getAllEventPaginationSortDate(Pageable pageable, String order) {
       if(pageable ==null){
            return null;
        }  
        Sort.Direction direction= Sort.Direction.ASC;
        if("desc".equalsIgnoreCase(order)){
            direction= Sort.Direction.DESC;
        }
        Pageable sortedPageable=PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(direction,"date")
        );
        return this.eventRepository.findAll(sortedPageable);
    }

    @Override
    public Page<Event> getAllEventPaginationSortCategorie(Pageable pageable, String order) {
        
        if(pageable ==null){
            return null;
        }  
        Sort.Direction direction= Sort.Direction.ASC;
        if("desc".equalsIgnoreCase(order)){
            direction= Sort.Direction.DESC;
        }
        Pageable sortedPageable=PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(direction,"categorie")
        );
        return this.eventRepository.findAll(sortedPageable);
    }
    
}
