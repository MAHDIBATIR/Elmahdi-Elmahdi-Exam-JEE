package ma.enset.elbatir_elmahdi.web;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditImmobilierDTO;
import ma.enset.elbatir_elmahdi.entities.PropertyType;
import ma.enset.elbatir_elmahdi.services.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits/immobilier")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditImmobilierRestController {
    private final CreditService creditService;

    @GetMapping
    public List<CreditImmobilierDTO> getAllCreditImmobiliers() {
        return creditService.getAllCreditImmobilier();
    }

    @PostMapping
    public CreditImmobilierDTO createCreditImmobilier(@RequestBody CreditImmobilierDTO creditImmobilierDTO) {
        return creditService.saveCreditImmobilier(creditImmobilierDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditImmobilierDTO> updateCreditImmobilier(@PathVariable Long id, @RequestBody CreditImmobilierDTO creditImmobilierDTO) {
        creditImmobilierDTO.setId(id);
        CreditImmobilierDTO updatedCredit = creditService.updateCreditImmobilier(creditImmobilierDTO);
        return ResponseEntity.ok(updatedCredit);
    }
} 