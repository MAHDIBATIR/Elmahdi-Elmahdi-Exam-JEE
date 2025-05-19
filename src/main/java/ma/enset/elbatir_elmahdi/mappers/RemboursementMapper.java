package ma.enset.elbatir_elmahdi.mappers;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.RemboursementDTO;
import ma.enset.elbatir_elmahdi.entities.Remboursement;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RemboursementMapper {
    
    public RemboursementDTO fromRemboursement(Remboursement remboursement) {
        if (remboursement == null) return null;
        
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remboursement.getId());
        dto.setDate(remboursement.getDate());
        dto.setAmount(remboursement.getAmount());
        dto.setType(remboursement.getType());
        
        if (remboursement.getCredit() != null) {
            dto.setCreditId(remboursement.getCredit().getId());
        }
        
        return dto;
    }
    
    public Remboursement fromRemboursementDTO(RemboursementDTO dto) {
        if (dto == null) return null;
        
        Remboursement remboursement = new Remboursement();
        remboursement.setId(dto.getId());
        remboursement.setDate(dto.getDate());
        remboursement.setAmount(dto.getAmount());
        remboursement.setType(dto.getType());
        
        return remboursement;
    }
} 