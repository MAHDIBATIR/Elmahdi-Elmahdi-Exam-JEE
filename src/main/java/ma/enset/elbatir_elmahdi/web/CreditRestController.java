package ma.enset.elbatir_elmahdi.web;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditDTO;
import ma.enset.elbatir_elmahdi.entities.CreditStatus;
import ma.enset.elbatir_elmahdi.services.CreditService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CreditRestController {
    private final CreditService creditService;

    // Generic credit endpoints
    @GetMapping
    public List<CreditDTO> getAllCredits() {
        return creditService.getAllCredits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        CreditDTO creditDTO = creditService.getCredit(id);
        if (creditDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditDTO);
    }

    @GetMapping("/client/{clientId}")
    public List<CreditDTO> getCreditsByClientId(@PathVariable Long clientId) {
        return creditService.getCreditsByClientId(clientId);
    }

    @GetMapping("/status/{status}")
    public List<CreditDTO> getCreditsByStatus(@PathVariable CreditStatus status) {
        return creditService.getCreditsByStatus(status);
    }

    @GetMapping("/date-range")
    public List<CreditDTO> getCreditsBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return creditService.getCreditsBetweenDates(startDate, endDate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }

    // Credit approval/rejection
    @PutMapping("/{id}/approve")
    public ResponseEntity<CreditDTO> approveCredit(@PathVariable Long id) {
        CreditDTO creditDTO = creditService.approveCreditRequest(id);
        if (creditDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditDTO);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<CreditDTO> rejectCredit(@PathVariable Long id) {
        CreditDTO creditDTO = creditService.rejectCreditRequest(id);
        if (creditDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditDTO);
    }

    // Credit calculations
    @GetMapping("/{id}/monthly-payment")
    public ResponseEntity<Map<String, Double>> calculateMonthlyPayment(@PathVariable Long id) {
        double monthlyPayment = creditService.calculateMonthlyPayment(id);
        return ResponseEntity.ok(Map.of("monthlyPayment", monthlyPayment));
    }

    @GetMapping("/{id}/total-interest")
    public ResponseEntity<Map<String, Double>> calculateTotalInterest(@PathVariable Long id) {
        double totalInterest = creditService.calculateTotalInterest(id);
        return ResponseEntity.ok(Map.of("totalInterest", totalInterest));
    }
} 