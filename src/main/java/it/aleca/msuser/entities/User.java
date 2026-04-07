package it.aleca.msuser.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import it.aleca.msuser.enums.UserStatusEnum;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "email" , unique = true, length = 255 , nullable = false)
    private String email;

    @Column(name = "password" , length = 255 , nullable = false)
    private String password;

    @Column(name = "status" , nullable = false)
    private UserStatusEnum status;

    @Column(name = "creationDate", nullable = false, updatable = false)
    private Instant creationDate;

    @Column(name = "lastModifiedDate" )
    private Instant lastModifiedDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<UserRole> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && status == user.status && Objects.equals(creationDate, user.creationDate) && Objects.equals(lastModifiedDate, user.lastModifiedDate) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, status, creationDate, lastModifiedDate, roles);
    }

    /** Sets date before insert */
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.creationDate = now;
        this.lastModifiedDate = now;
    }

    /** Update date before updae */
    @PreUpdate
    protected void onUpdate() {
        this.lastModifiedDate = Instant.now();
    }

    public void addRole(Role role) {
        UserRole userRole = new UserRole(this, role);
        roles.add(userRole);
    }

    public void removeRole(Role role) {
        roles.removeIf(ur -> ur.getRole().equals(role));
    }

}
