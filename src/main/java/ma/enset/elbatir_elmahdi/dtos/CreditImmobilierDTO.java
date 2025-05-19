package ma.enset.elbatir_elmahdi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.elbatir_elmahdi.entities.PropertyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditImmobilierDTO extends CreditDTO {
    private PropertyType propertyType;
} 