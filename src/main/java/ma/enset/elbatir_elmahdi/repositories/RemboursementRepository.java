package ma.enset.elbatir_elmahdi.repositories;

import ma.enset.elbatir_elmahdi.entities.Remboursement;
import ma.enset.elbatir_elmahdi.entities.RemboursementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCredit_Id(Long creditId);
    List<Remboursement> findByType(RemboursementType type);
    List<Remboursement> findByDateBetween(Date startDate, Date endDate);
} 