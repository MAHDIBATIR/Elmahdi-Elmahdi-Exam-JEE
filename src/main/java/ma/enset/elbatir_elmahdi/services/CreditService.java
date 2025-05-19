package ma.enset.elbatir_elmahdi.services;

import ma.enset.elbatir_elmahdi.dtos.CreditDTO;
import ma.enset.elbatir_elmahdi.dtos.CreditImmobilierDTO;
import ma.enset.elbatir_elmahdi.dtos.CreditPersonnelDTO;
import ma.enset.elbatir_elmahdi.dtos.CreditProfessionnelDTO;
import ma.enset.elbatir_elmahdi.entities.CreditStatus;

import java.util.Date;
import java.util.List;

public interface CreditService {
    
    // Generic credit operations
    CreditDTO getCredit(Long id);
    List<CreditDTO> getAllCredits();
    void deleteCredit(Long id);
    List<CreditDTO> getCreditsByClientId(Long clientId);
    List<CreditDTO> getCreditsByStatus(CreditStatus status);
    List<CreditDTO> getCreditsBetweenDates(Date startDate, Date endDate);
    
    // Credit Personnel operations
    CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO creditDTO);
    CreditPersonnelDTO updateCreditPersonnel(CreditPersonnelDTO creditDTO);
    List<CreditPersonnelDTO> getAllCreditPersonnel();
    List<CreditPersonnelDTO> getCreditPersonnelByMotif(String motif);
    
    // Credit Immobilier operations
    CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO creditDTO);
    CreditImmobilierDTO updateCreditImmobilier(CreditImmobilierDTO creditDTO);
    List<CreditImmobilierDTO> getAllCreditImmobilier();
    
    // Credit Professionnel operations
    CreditProfessionnelDTO saveCreditProfessionnel(CreditProfessionnelDTO creditDTO);
    CreditProfessionnelDTO updateCreditProfessionnel(CreditProfessionnelDTO creditDTO);
    List<CreditProfessionnelDTO> getAllCreditProfessionnel();
    List<CreditProfessionnelDTO> getCreditProfessionnelByRaisonSociale(String raisonSociale);
    
    // Business operations
    CreditDTO approveCreditRequest(Long creditId);
    CreditDTO rejectCreditRequest(Long creditId);
    double calculateTotalInterest(Long creditId);
    double calculateMonthlyPayment(Long creditId);
} 