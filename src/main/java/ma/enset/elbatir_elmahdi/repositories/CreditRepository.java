package ma.enset.elbatir_elmahdi.repositories;

import ma.enset.elbatir_elmahdi.entities.Credit;
import ma.enset.elbatir_elmahdi.entities.CreditStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClient_Id(Long clientId);
    List<Credit> findByStatus(CreditStatus status);
    List<Credit> findByRequestDateBetween(Date startDate, Date endDate);
} 