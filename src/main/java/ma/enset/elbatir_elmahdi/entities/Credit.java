package ma.enset.elbatir_elmahdi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date requestDate;
    
    @Enumerated(EnumType.STRING)
    private CreditStatus status;
    
    @Temporal(TemporalType.DATE)
    private Date acceptanceDate;
    
    private Double amount;
    private Integer duration; // Duration in months
    private Double interestRate;
    
    @ManyToOne
    private Client client;
    
    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY)
    private List<Remboursement> remboursements;
} 