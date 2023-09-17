package by.aleksandrphilimonov.jwtappdemo.repository;

import by.aleksandrphilimonov.jwtappdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
