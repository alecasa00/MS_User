package it.aleca.msuser.services.impl;

import it.aleca.msuser.entities.Role;
import it.aleca.msuser.repository.RoleRepository;
import it.aleca.msuser.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    //this method should remain accessible only from service package
    protected List<Role> getRolesByIdList(List<Long> roleIds) {
        //assuring no duplicates in list with distinct
        List<Role> roles = roleRepository.findDistinctByIdInOrderByIdAsc(roleIds);

        //if roles is empty return null, else roles
        return roles.isEmpty() ? null : roles;
    }

}
