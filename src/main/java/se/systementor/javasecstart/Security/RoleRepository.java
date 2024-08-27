package se.systementor.javasecstart.Security;

import org.springframework.data.repository.CrudRepository;
import se.systementor.javasecstart.model.Role;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role findByName(String name);
}
