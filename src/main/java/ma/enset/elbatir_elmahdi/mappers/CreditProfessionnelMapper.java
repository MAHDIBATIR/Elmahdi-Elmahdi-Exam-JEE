package ma.enset.elbatir_elmahdi.mappers;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditProfessionnelDTO;
import ma.enset.elbatir_elmahdi.entities.CreditProfessionnel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditProfessionnelMapper {
    private final CreditMapper creditMapper;
    
    public CreditProfessionnelDTO fromCreditProfessionnel(CreditProfessionnel creditProfessionnel) {
        if (creditProfessionnel == null) return null;
        
        CreditProfessionnelDTO dto = new CreditProfessionnelDTO();
        
        // Copy base credit properties
        dto.setId(creditProfessionnel.getId());
        dto.setRequestDate(creditProfessionnel.getRequestDate());
        dto.setStatus(creditProfessionnel.getStatus());
        dto.setAcceptanceDate(creditProfessionnel.getAcceptanceDate());
        dto.setAmount(creditProfessionnel.getAmount());
        dto.setDuration(creditProfessionnel.getDuration());
        dto.setInterestRate(creditProfessionnel.getInterestRate());
        
        if (creditProfessionnel.getClient() != null) {
            dto.setClientId(creditProfessionnel.getClient().getId());
            dto.setClientName(creditProfessionnel.getClient().getName());
        }
        
        // Set specific properties
        dto.setMotif(creditProfessionnel.getMotif());
        dto.setRaisonSociale(creditProfessionnel.getRaisonSociale());
        
        return dto;
    }
    
    public CreditProfessionnel fromCreditProfessionnelDTO(CreditProfessionnelDTO dto) {
        if (dto == null) return null;
        
        CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
        
        // Set base credit properties
        creditProfessionnel.setId(dto.getId());
        creditProfessionnel.setRequestDate(dto.getRequestDate());
        creditProfessionnel.setStatus(dto.getStatus());
        creditProfessionnel.setAcceptanceDate(dto.getAcceptanceDate());
        creditProfessionnel.setAmount(dto.getAmount());
        creditProfessionnel.setDuration(dto.getDuration());
        creditProfessionnel.setInterestRate(dto.getInterestRate());
        
        // Set specific properties
        creditProfessionnel.setMotif(dto.getMotif());
        creditProfessionnel.setRaisonSociale(dto.getRaisonSociale());
        
        return creditProfessionnel;
    }
} 