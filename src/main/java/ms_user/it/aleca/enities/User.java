package ms_user.it.aleca.enities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
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

    @Column(name = "active" , unique = true , nullable = false)
    private Boolean active;

    @Column(name = "creationDate", nullable = false, updatable = false)
    private Instant creationDate;

    @Column(name = "lastModifiedDate" )
    private Instant lastModifiedDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = @UniqueConstraint(
                    name = "uk_user_role",
                    columnNames = {"user_id", "role_id"}
            )
    )
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
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

}
