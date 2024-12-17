package com.example.miniprojet.web.controllers;





import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.miniprojet.web.models.User;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


/* 
    espaceuser                  :	GET	(/user)	Récupère la liste des événements avec pagination.
    espaceuserfilterDate        :	GET	(/user/filter/date)	Récupère la liste des événements triée par date avec pagination.
    espaceuserfilterCategorie    :	GET	(/user/filter/date)	Récupère la liste des événements triée par Categorie avec pagination.
    showajoutuserevent          :	GET	(/user/{id}/ajout)	Affiche la page d'ajout d'un utilisateur à un événement spécifique.	
    ajoutuserevent	            :   POST(/user/{id}/ajout)	Ajoute un utilisateur à un événement, met à jour le nombre de places restantes, et redirige vers /.
 */



@Controller
@RequestMapping("/user")
public class UserController {



    @Autowired
    private EventService eventService ;
   /*  public UserController(EventService eventService){
        this.eventService = eventService;
    } */
    @Autowired
    private RegisterService registerService ;
   /*  public UserController(RegisterService registerService){
        this.registerService = registerService ;
    }
 */

   

 @GetMapping()
    public String espaceuser(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "3") int pageSize,
                            Model model)
    {

        Page<Event> userPage = this.eventService.getAllEventPagination(PageRequest.of(page, pageSize));
        model.addAttribute("events", userPage.getContent());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        return "user/user" ;
    }


    @GetMapping("/filter/date")
    public String espaceuserfilterDate( @RequestParam(required = false, defaultValue = "asc") String sortByDate,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "3") int pageSize,
                                    Model model)
    {
        Page<Event> eventPage = this.eventService.getAllEventPaginationSortDate(PageRequest.of(page, pageSize), sortByDate);
        model.addAttribute("events", eventPage.getContent());
        model.addAttribute("sortByDate", sortByDate);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventPage.getTotalPages());
        return "user/user" ;
    }

    @GetMapping("/filter/categorie")
    public String espaceuserfilterCategorie( @RequestParam(required = false, defaultValue = "asc") String sortByCategorie,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "3") int pageSize,
                                    Model model)
    {
        Page<Event> eventPage = this.eventService.getAllEventPaginationSortCategorie(PageRequest.of(page, pageSize), sortByCategorie);
        model.addAttribute("events", eventPage.getContent());
        model.addAttribute("sortByCategorie", sortByCategorie);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", eventPage.getTotalPages());
        return "user/user" ;
    }


    @GetMapping("/{id}/ajout")
    public String showajoutuserevent (@PathVariable Long id ,
                                      Model model) {
         
        model.addAttribute("user", new User());
        model.addAttribute("id", id);                           
        return "user/ajout-user";
    }
    

    @PostMapping("/{id}/ajout")
    public String ajoutuserevent(@PathVariable Long id,
                                 @Valid @ModelAttribute User user,
                                 BindingResult bindingResult,
                                 Model model) {
        
        if(bindingResult.hasErrors())
        {
            model.addAttribute("error", "Invalid Input");
            return "ajout-user"; 
        }
        Register r = new Register(null, id, user.getNom(), user.getEmail(), user.getTelephone(), user.getPaiment());
        if((this.registerService.getuserbyemail(user.getEmail()) == 0)&&(this.eventService.getEventById(id).getNbplace()!=0))
        {
            this.registerService.addUser(r);
            Event e = this.eventService.getEventById(id);
            e.setNbplace(e.getNbplace()-1);
            this.eventService.updateEvent(e);
        }
        return "redirect:/user";
    }


    
    
}
