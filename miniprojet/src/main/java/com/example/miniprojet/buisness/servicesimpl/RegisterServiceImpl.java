package com.example.miniprojet.buisness.servicesimpl;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.miniprojet.buisness.services.RegisterService;
import com.example.miniprojet.dao.entitys.Register;
import com.example.miniprojet.dao.repository.RegisterRepository;
import com.example.miniprojet.exception.BadRequestException;
import com.example.miniprojet.exception.UserNotFoundException;


@Service
public class RegisterServiceImpl implements RegisterService {

    private final RegisterRepository registerRepository ;

    public RegisterServiceImpl(RegisterRepository registerRepository){
        this.registerRepository = registerRepository ;
    }

    @Override
    public Register addUser(Register register) {
        try {
            return this.registerRepository.save(register);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public List<Register> getallUsersbyid(Long id) {
            try {
                return registerRepository.findByIdevent(id);
            } catch (Exception exception) {
                throw new RuntimeException(exception.getMessage());
            }

            /* if(id == null){
                return null ;
            }else
                return this.registerRepository.findByIdevent(id); */
         
    }

    @Override
    public void deleteUserById(Long id) {
        if(id == null){
            return;
        }
        else if(this.registerRepository.existsById(id)){
            this.registerRepository.deleteById(id);
        }else{
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public Page<Register> getAllUserPaginationByid(Pageable pegeable, Long id) {
        
        return this.registerRepository.findByIdevent(id, pegeable);
    }

    @Override
    public Integer getuserbyemail(String email) {
        if(email == null){
            return null;
        }
        Integer k ;
        try {
            k = this.registerRepository.findByEmail(email).size();
            return k ;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
         
    }

    

    
    
}
