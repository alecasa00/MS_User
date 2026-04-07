package it.aleca.msuser.repository;

import lombok.NonNull;
import it.aleca.msuser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(@NonNull String email);
}
