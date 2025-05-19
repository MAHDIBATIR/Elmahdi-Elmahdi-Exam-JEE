package ma.enset.elbatir_elmahdi.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditProfessionnel extends Credit {
    private String motif; // Purpose of the loan
    private String raisonSociale; // Company name
} 