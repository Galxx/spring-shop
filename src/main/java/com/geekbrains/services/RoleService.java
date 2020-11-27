package com.geekbrains.services;

import com.geekbrains.controllers.dto.UserType;
import com.geekbrains.entities.Role;
import com.geekbrains.exceptions.RoleNotFoundException;
import com.geekbrains.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getByName(String name) {
        Optional<Role> roleOptional = roleRepository.findByName(name);

        if(roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            throw new RoleNotFoundException(String.format("Роль с именем %s не найдена.", name));
        }
    }

    public Role getByUserType(UserType userType) {

        String name;
        name = "ROLE_";

        if (userType == UserType.MANAGER){
            name = "ROLE_MANAGER";
        }else if(userType == UserType.USER)
        {
            name = "ROLE_CUSTOMER";
        }

        Optional<Role> roleOptional = roleRepository.findByName(name);

        if(roleOptional.isPresent()) {
            return roleOptional.get();
        } else {
            throw new RoleNotFoundException(String.format("Роль с именем %s не найдена.", name));
        }
    }



}
