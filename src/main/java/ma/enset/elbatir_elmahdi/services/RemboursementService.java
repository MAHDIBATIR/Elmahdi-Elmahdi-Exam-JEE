package ma.enset.elbatir_elmahdi.services;

import ma.enset.elbatir_elmahdi.dtos.RemboursementDTO;
import ma.enset.elbatir_elmahdi.entities.RemboursementType;

import java.util.Date;
import java.util.List;

public interface RemboursementService {
    
    // CRUD operations
    RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO);
    RemboursementDTO getRemboursement(Long id);
    List<RemboursementDTO> getAllRemboursements();
    RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO);
    void deleteRemboursement(Long id);
    
    // Additional operations
    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
    List<RemboursementDTO> getRemboursementsByType(RemboursementType type);
    List<RemboursementDTO> getRemboursementsBetweenDates(Date startDate, Date endDate);
    
    // Business operations
    double calculateTotalRemboursementsForCredit(Long creditId);
    double calculateRemainingAmountForCredit(Long creditId);
} 