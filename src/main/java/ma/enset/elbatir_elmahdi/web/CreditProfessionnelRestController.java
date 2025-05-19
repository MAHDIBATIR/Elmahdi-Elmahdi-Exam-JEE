package ma.enset.elbatir_elmahdi.web;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditProfessionnelDTO;
import ma.enset.elbatir_elmahdi.services.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits/professionnel")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditProfessionnelRestController {
    private final CreditService creditService;

    @GetMapping
    public List<CreditProfessionnelDTO> getAllCreditProfessionnels() {
        return creditService.getAllCreditProfessionnel();
    }

    @GetMapping("/raison-sociale/{raisonSociale}")
    public List<CreditProfessionnelDTO> getCreditProfessionnelsByRaisonSociale(@PathVariable String raisonSociale) {
        return creditService.getCreditProfessionnelByRaisonSociale(raisonSociale);
    }

    @PostMapping
    public CreditProfessionnelDTO createCreditProfessionnel(@RequestBody CreditProfessionnelDTO creditProfessionnelDTO) {
        return creditService.saveCreditProfessionnel(creditProfessionnelDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditProfessionnelDTO> updateCreditProfessionnel(@PathVariable Long id, @RequestBody CreditProfessionnelDTO creditProfessionnelDTO) {
        creditProfessionnelDTO.setId(id);
        CreditProfessionnelDTO updatedCredit = creditService.updateCreditProfessionnel(creditProfessionnelDTO);
        return ResponseEntity.ok(updatedCredit);
    }
} 