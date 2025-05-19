package ma.enset.elbatir_elmahdi.repositories;

import ma.enset.elbatir_elmahdi.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
} 