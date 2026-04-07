package it.aleca.msuser.repository;

import it.aleca.msuser.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findDistinctByIdInOrderByIdAsc(Collection<Long> ids);
}
