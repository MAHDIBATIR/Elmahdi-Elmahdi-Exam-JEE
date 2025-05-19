package ma.enset.elbatir_elmahdi.web;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.RemboursementDTO;
import ma.enset.elbatir_elmahdi.entities.RemboursementType;
import ma.enset.elbatir_elmahdi.services.RemboursementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/remboursements")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RemboursementRestController {
    private final RemboursementService remboursementService;

    @GetMapping
    public List<RemboursementDTO> getAllRemboursements() {
        return remboursementService.getAllRemboursements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        RemboursementDTO remboursementDTO = remboursementService.getRemboursement(id);
        if (remboursementDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(remboursementDTO);
    }

    @GetMapping("/credit/{creditId}")
    public List<RemboursementDTO> getRemboursementsByCreditId(@PathVariable Long creditId) {
        return remboursementService.getRemboursementsByCreditId(creditId);
    }

    @GetMapping("/type/{type}")
    public List<RemboursementDTO> getRemboursementsByType(@PathVariable RemboursementType type) {
        return remboursementService.getRemboursementsByType(type);
    }

    @GetMapping("/date-range")
    public List<RemboursementDTO> getRemboursementsBetweenDates(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return remboursementService.getRemboursementsBetweenDates(startDate, endDate);
    }

    @PostMapping
    public RemboursementDTO createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return remboursementService.saveRemboursement(remboursementDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemboursementDTO> updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO remboursementDTO) {
        remboursementDTO.setId(id);
        RemboursementDTO updatedRemboursement = remboursementService.updateRemboursement(remboursementDTO);
        return ResponseEntity.ok(updatedRemboursement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/credit/{creditId}/total")
    public ResponseEntity<Map<String, Double>> calculateTotalRemboursements(@PathVariable Long creditId) {
        double totalRemboursements = remboursementService.calculateTotalRemboursementsForCredit(creditId);
        return ResponseEntity.ok(Map.of("totalRemboursements", totalRemboursements));
    }

    @GetMapping("/credit/{creditId}/remaining")
    public ResponseEntity<Map<String, Double>> calculateRemainingAmount(@PathVariable Long creditId) {
        double remainingAmount = remboursementService.calculateRemainingAmountForCredit(creditId);
        return ResponseEntity.ok(Map.of("remainingAmount", remainingAmount));
    }
} 