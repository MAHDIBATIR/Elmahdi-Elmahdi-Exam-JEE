package ma.enset.elbatir_elmahdi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.elbatir_elmahdi.entities.RemboursementType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemboursementDTO {
    private Long id;
    private Date date;
    private Double amount;
    private RemboursementType type;
    private Long creditId;
} 