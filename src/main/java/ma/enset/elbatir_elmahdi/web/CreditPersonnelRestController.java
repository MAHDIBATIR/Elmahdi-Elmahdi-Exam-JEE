package ma.enset.elbatir_elmahdi.web;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditPersonnelDTO;
import ma.enset.elbatir_elmahdi.services.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits/personnel")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditPersonnelRestController {
    private final CreditService creditService;

    @GetMapping
    public List<CreditPersonnelDTO> getAllCreditPersonnels() {
        return creditService.getAllCreditPersonnel();
    }

    @GetMapping("/motif/{motif}")
    public List<CreditPersonnelDTO> getCreditPersonnelByMotif(@PathVariable String motif) {
        return creditService.getCreditPersonnelByMotif(motif);
    }

    @PostMapping
    public CreditPersonnelDTO createCreditPersonnel(@RequestBody CreditPersonnelDTO creditPersonnelDTO) {
        return creditService.saveCreditPersonnel(creditPersonnelDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditPersonnelDTO> updateCreditPersonnel(@PathVariable Long id, @RequestBody CreditPersonnelDTO creditPersonnelDTO) {
        creditPersonnelDTO.setId(id);
        CreditPersonnelDTO updatedCredit = creditService.updateCreditPersonnel(creditPersonnelDTO);
        return ResponseEntity.ok(updatedCredit);
    }
} 