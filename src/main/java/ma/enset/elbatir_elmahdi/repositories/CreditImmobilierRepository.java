package ma.enset.elbatir_elmahdi.repositories;

import ma.enset.elbatir_elmahdi.entities.CreditImmobilier;
import ma.enset.elbatir_elmahdi.entities.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByPropertyType(PropertyType propertyType);
} 