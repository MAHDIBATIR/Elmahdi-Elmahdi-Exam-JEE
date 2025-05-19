package ma.enset.elbatir_elmahdi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.elbatir_elmahdi.entities.CreditStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private Long id;
    private Date requestDate;
    private CreditStatus status;
    private Date acceptanceDate;
    private Double amount;
    private Integer duration;
    private Double interestRate;
    private Long clientId;
    private String clientName;
} 