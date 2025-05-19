package ma.enset.elbatir_elmahdi.mappers;

import ma.enset.elbatir_elmahdi.dtos.CreditDTO;
import ma.enset.elbatir_elmahdi.entities.Credit;
import org.springframework.stereotype.Component;

@Component
public class CreditMapper {
    
    public CreditDTO fromCredit(Credit credit) {
        if (credit == null) return null;
        
        CreditDTO creditDTO = new CreditDTO();
        creditDTO.setId(credit.getId());
        creditDTO.setRequestDate(credit.getRequestDate());
        creditDTO.setStatus(credit.getStatus());
        creditDTO.setAcceptanceDate(credit.getAcceptanceDate());
        creditDTO.setAmount(credit.getAmount());
        creditDTO.setDuration(credit.getDuration());
        creditDTO.setInterestRate(credit.getInterestRate());
        
        if (credit.getClient() != null) {
            creditDTO.setClientId(credit.getClient().getId());
            creditDTO.setClientName(credit.getClient().getName());
        }
        
        return creditDTO;
    }
    
    public Credit fromCreditDTO(CreditDTO creditDTO) {
        if (creditDTO == null) return null;
        
        Credit credit = new Credit();
        credit.setId(creditDTO.getId());
        credit.setRequestDate(creditDTO.getRequestDate());
        credit.setStatus(creditDTO.getStatus());
        credit.setAcceptanceDate(creditDTO.getAcceptanceDate());
        credit.setAmount(creditDTO.getAmount());
        credit.setDuration(creditDTO.getDuration());
        credit.setInterestRate(creditDTO.getInterestRate());
        
        return credit;
    }
} 