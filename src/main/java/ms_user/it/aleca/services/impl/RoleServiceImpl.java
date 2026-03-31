package ms_user.it.aleca.services.impl;

import ms_user.it.aleca.entities.Role;
import ms_user.it.aleca.repository.RoleRepository;
import ms_user.it.aleca.services.RoleService;

import java.util.List;

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
