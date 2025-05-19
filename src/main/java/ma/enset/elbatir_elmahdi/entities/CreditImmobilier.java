package ma.enset.elbatir_elmahdi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType; // Type of property: Apartment, House, or Commercial premises
}

enum PropertyType {
    APPARTEMENT, MAISON, LOCAL_COMMERCIAL
} 