package com.example.miniprojet.web.controllers;




import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.miniprojet.buisness.services.EventService;
import com.example.miniprojet.buisness.services.RegisterService;
import com.example.miniprojet.dao.entitys.Event;
import com.example.miniprojet.dao.entitys.Register;
import com.example.miniprojet.web.models.EventForm;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/* 
showEventListe:         GET	(/espaceadmin)	Affiche une liste paginée des événements existants.
showAddEventForm:	    GET	(/espaceadmin/add)	Retourne un formulaire permettant de créer un nouvel événement.
addEven:	            POST	(/espaceadmin/add)	Ajoute un nouvel événement avec validation des données et gestion d'image.
showEditevent:	        GET	(/espaceadmin/{id}/edit)	Retourne un formulaire pré-rempli pour modifier les détails d'un événement identifié par id.
editevent:	            POST	(/espaceadmin/{id}/edit)	Met à jour un événement existant avec de nouvelles informations et gestion de l'image.
deleteevent:	        POST	(/espaceadmin/{id}/delete)	Supprime un événement identifié par id, ainsi que son fichier image associé, s'il existe.
ListeParticipant:	    GET	(/espaceadmin/{id}/participant)	Affiche une liste paginée des participants inscrits pour l'événement identifié par id.
AnnulationParticipant:  POST	(/espaceadmin/{id}/participant/{idu}/delete)	Supprime un participant identifié par idu et met à jour le nombre de places disponibles.
 */



@Controller
@RequestMapping("/espaceadmin")
public class EventController {

    private final EventService eventService ;
    private final RegisterService registerService ;
    public EventController(EventService eventService , RegisterService registerService ){
        this.eventService = eventService;
        this.registerService = registerService ;
    }

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/image";


   /*  @RequestMapping("/auth")
    public String showauthadmin(Model model) {
        model.addAttribute("admin", new Admin());
        return "authadmin";
    }

    @PostMapping("/auth")
    public String authadmin(@Valid @ModelAttribute Admin admin,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid Input");
            return "authadmin";
        }
        if ((admin.getName().equals("admin")) & (admin.getPassword().equals("admin"))) {

            return "redirect:/auth_admin/espaceadmin";
        }
        return "authadmin";
    } */

    @GetMapping
    public String showEventListe(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "2") int pageSize,
                                    Model model) {

        Page<Event> eventPage = this.eventService.getAllEventPagination(PageRequest.of(page, pageSize));
        model.addAttribute("events", eventPage.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventPage.getTotalPages());

        return "event/espaceadmin";
    }

    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("eventForm", new EventForm());
        return "event/add-event";
    }

    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute EventForm eventForm,
            BindingResult bindingResult,
            Model model,
            @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid Input");
            return "event/add-event";
        }

         if (!file.isEmpty()) {
            StringBuilder fileName = new StringBuilder();
            fileName.append(file.getOriginalFilename());
            Path newFilePath = Path.of(uploadDirectory, fileName.toString());

            try {
                Files.write(newFilePath, file.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
          //  persons.add(new Person(++idCount, personForm.getName(), personForm.getAge(), fileName.toString()));
           this.eventService.addEvent(new Event(null, eventForm.getTitre(), eventForm.getDescription(), eventForm.getDate(),
           eventForm.getLieu(), eventForm.getNbplace(), eventForm.getPrix(), eventForm.getCategorie(),fileName.toString()));    

        } else
        {
            this.eventService.addEvent(new Event(null, eventForm.getTitre(), eventForm.getDescription(), eventForm.getDate(),
            eventForm.getLieu(), eventForm.getNbplace(), eventForm.getPrix(), eventForm.getCategorie(),"no-image.png"));    
        }
    
        return "redirect:/espaceadmin";
    }

    @GetMapping("/{id}/edit")
    public String showEditevent(@PathVariable Long id, Model model) {
        Event e = this.eventService.getEventById(id);
        model.addAttribute("eventForm", new EventForm(e.getTitre(), e.getDescription(), e.getDate(),
            e.getLieu(), e.getNbplace(), e.getPrix(), e.getCategorie(),e.getImage()));
        model.addAttribute("id", id);


        return "event/edit-event";
    }

    @PostMapping("/{id}/edit")
    public String editevent(@PathVariable Long id,
            @Valid @ModelAttribute EventForm eventForm,
            BindingResult bindingResult,
            Model model,
            @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Invalid Input");
            return "event/edit-event";
        }
       
           List<Register> k = this.registerService.getallUsersbyid(id);
           Event e = this.eventService.getEventById(id);
           e.setTitre(eventForm.getTitre());
           e.setDescription(eventForm.getDescription());
           e.setDate(eventForm.getDate());
           e.setLieu(eventForm.getLieu());
           if(e.getNbplace() == eventForm.getNbplace())
                e.setNbplace(eventForm.getNbplace());
            else 
                e.setNbplace(eventForm.getNbplace()-k.size());
           e.setPrix(eventForm.getPrix());
           e.setCategorie(eventForm.getCategorie());
           if (!file.isEmpty()) {
            StringBuilder fileName = new StringBuilder();
            Path newFilePath = Path.of(uploadDirectory, file.getOriginalFilename());
            fileName.append(file.getOriginalFilename());
            try {
                Files.write(newFilePath, file.getBytes());
            } catch (Exception w) {
                w.printStackTrace();
            }
            e.setImage(fileName.toString());
        }
           this.eventService.addEvent(e);


        return "redirect:/espaceadmin";
    }

    @PostMapping("/{id}/delete")
    public String deleteevent(@PathVariable Long id) {
  
        String filename=this.eventService.getEventById(id).getImage();
        if(!filename.equals("no-image.png")){
            Path filePath = Path.of(uploadDirectory, filename);
        try {
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
    
        this.eventService.deleteEventById(id);
        this.registerService.deleteUserById(id);
        return "redirect:/espaceadmin";
    }

    @GetMapping("/{id}/participant")
    public String ListeParticipant( @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "4") int pageSize,
                                    @PathVariable Long id ,
                                    Model model) {


        model.addAttribute("id", id);
        Page<Register> userPage = this.registerService.getAllUserPaginationByid(PageRequest.of(page, pageSize),id);
        model.addAttribute("liste",userPage.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());


        return "event/liste-participant";
    }

    @PostMapping("/{id}/participant/{idu}/delete")
    public String AnnulationParticipant(@PathVariable Long id , @PathVariable Long idu) {
        this.registerService.deleteUserById(idu);

        Event e =this.eventService.getEventById(id);
        e.setNbplace(e.getNbplace()+1);
        this.eventService.updateEvent(e);
        return "redirect:/espaceadmin/{id}/participant";
    }
    
    

}
