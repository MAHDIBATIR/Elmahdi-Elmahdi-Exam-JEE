package ma.enset.elbatir_elmahdi.repositories;

import ma.enset.elbatir_elmahdi.entities.CreditPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditPersonnelRepository extends JpaRepository<CreditPersonnel, Long> {
    List<CreditPersonnel> findByMotifContaining(String motif);
} 