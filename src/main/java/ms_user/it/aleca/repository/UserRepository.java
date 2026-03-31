package ms_user.it.aleca.repository;

import lombok.NonNull;
import ms_user.it.aleca.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(@NonNull String email);
}
