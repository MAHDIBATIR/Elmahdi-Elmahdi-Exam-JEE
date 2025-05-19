package ma.enset.elbatir_elmahdi.mappers;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditPersonnelDTO;
import ma.enset.elbatir_elmahdi.entities.CreditPersonnel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditPersonnelMapper {
    private final CreditMapper creditMapper;
    
    public CreditPersonnelDTO fromCreditPersonnel(CreditPersonnel creditPersonnel) {
        if (creditPersonnel == null) return null;
        
        CreditPersonnelDTO dto = new CreditPersonnelDTO();
        
        // Copy base credit properties
        dto.setId(creditPersonnel.getId());
        dto.setRequestDate(creditPersonnel.getRequestDate());
        dto.setStatus(creditPersonnel.getStatus());
        dto.setAcceptanceDate(creditPersonnel.getAcceptanceDate());
        dto.setAmount(creditPersonnel.getAmount());
        dto.setDuration(creditPersonnel.getDuration());
        dto.setInterestRate(creditPersonnel.getInterestRate());
        
        if (creditPersonnel.getClient() != null) {
            dto.setClientId(creditPersonnel.getClient().getId());
            dto.setClientName(creditPersonnel.getClient().getName());
        }
        
        // Set specific properties
        dto.setMotif(creditPersonnel.getMotif());
        
        return dto;
    }
    
    public CreditPersonnel fromCreditPersonnelDTO(CreditPersonnelDTO dto) {
        if (dto == null) return null;
        
        CreditPersonnel creditPersonnel = new CreditPersonnel();
        
        // Set base credit properties
        creditPersonnel.setId(dto.getId());
        creditPersonnel.setRequestDate(dto.getRequestDate());
        creditPersonnel.setStatus(dto.getStatus());
        creditPersonnel.setAcceptanceDate(dto.getAcceptanceDate());
        creditPersonnel.setAmount(dto.getAmount());
        creditPersonnel.setDuration(dto.getDuration());
        creditPersonnel.setInterestRate(dto.getInterestRate());
        
        // Set specific properties
        creditPersonnel.setMotif(dto.getMotif());
        
        return creditPersonnel;
    }
} 