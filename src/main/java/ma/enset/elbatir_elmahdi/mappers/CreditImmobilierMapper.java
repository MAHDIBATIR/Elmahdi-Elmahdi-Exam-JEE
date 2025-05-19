package ma.enset.elbatir_elmahdi.mappers;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditImmobilierDTO;
import ma.enset.elbatir_elmahdi.entities.CreditImmobilier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditImmobilierMapper {
    private final CreditMapper creditMapper;
    
    public CreditImmobilierDTO fromCreditImmobilier(CreditImmobilier creditImmobilier) {
        if (creditImmobilier == null) return null;
        
        CreditImmobilierDTO dto = new CreditImmobilierDTO();
        
        // Copy base credit properties
        dto.setId(creditImmobilier.getId());
        dto.setRequestDate(creditImmobilier.getRequestDate());
        dto.setStatus(creditImmobilier.getStatus());
        dto.setAcceptanceDate(creditImmobilier.getAcceptanceDate());
        dto.setAmount(creditImmobilier.getAmount());
        dto.setDuration(creditImmobilier.getDuration());
        dto.setInterestRate(creditImmobilier.getInterestRate());
        
        if (creditImmobilier.getClient() != null) {
            dto.setClientId(creditImmobilier.getClient().getId());
            dto.setClientName(creditImmobilier.getClient().getName());
        }
        
        // Set specific properties
        dto.setPropertyType(creditImmobilier.getPropertyType());
        
        return dto;
    }
    
    public CreditImmobilier fromCreditImmobilierDTO(CreditImmobilierDTO dto) {
        if (dto == null) return null;
        
        CreditImmobilier creditImmobilier = new CreditImmobilier();
        
        // Set base credit properties
        creditImmobilier.setId(dto.getId());
        creditImmobilier.setRequestDate(dto.getRequestDate());
        creditImmobilier.setStatus(dto.getStatus());
        creditImmobilier.setAcceptanceDate(dto.getAcceptanceDate());
        creditImmobilier.setAmount(dto.getAmount());
        creditImmobilier.setDuration(dto.getDuration());
        creditImmobilier.setInterestRate(dto.getInterestRate());
        
        // Set specific properties
        creditImmobilier.setPropertyType(dto.getPropertyType());
        
        return creditImmobilier;
    }
} 