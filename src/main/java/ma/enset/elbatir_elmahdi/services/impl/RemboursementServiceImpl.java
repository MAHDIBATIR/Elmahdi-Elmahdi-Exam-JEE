package ma.enset.elbatir_elmahdi.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.RemboursementDTO;
import ma.enset.elbatir_elmahdi.entities.Credit;
import ma.enset.elbatir_elmahdi.entities.Remboursement;
import ma.enset.elbatir_elmahdi.entities.RemboursementType;
import ma.enset.elbatir_elmahdi.mappers.RemboursementMapper;
import ma.enset.elbatir_elmahdi.repositories.CreditRepository;
import ma.enset.elbatir_elmahdi.repositories.RemboursementRepository;
import ma.enset.elbatir_elmahdi.services.RemboursementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RemboursementServiceImpl implements RemboursementService {
    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;
    private final RemboursementMapper remboursementMapper;

    @Override
    public RemboursementDTO saveRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.fromRemboursementDTO(remboursementDTO);
        // Set credit reference
        if (remboursementDTO.getCreditId() != null) {
            Credit credit = creditRepository.findById(remboursementDTO.getCreditId()).orElse(null);
            remboursement.setCredit(credit);
        }
        Remboursement savedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.fromRemboursement(savedRemboursement);
    }

    @Override
    public RemboursementDTO getRemboursement(Long id) {
        Remboursement remboursement = remboursementRepository.findById(id).orElse(null);
        return remboursementMapper.fromRemboursement(remboursement);
    }

    @Override
    public List<RemboursementDTO> getAllRemboursements() {
        List<Remboursement> remboursements = remboursementRepository.findAll();
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public RemboursementDTO updateRemboursement(RemboursementDTO remboursementDTO) {
        Remboursement remboursement = remboursementMapper.fromRemboursementDTO(remboursementDTO);
        // Set credit reference
        if (remboursementDTO.getCreditId() != null) {
            Credit credit = creditRepository.findById(remboursementDTO.getCreditId()).orElse(null);
            remboursement.setCredit(credit);
        }
        Remboursement updatedRemboursement = remboursementRepository.save(remboursement);
        return remboursementMapper.fromRemboursement(updatedRemboursement);
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByCreditId(Long creditId) {
        List<Remboursement> remboursements = remboursementRepository.findByCredit_Id(creditId);
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsByType(RemboursementType type) {
        List<Remboursement> remboursements = remboursementRepository.findByType(type);
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemboursementDTO> getRemboursementsBetweenDates(Date startDate, Date endDate) {
        List<Remboursement> remboursements = remboursementRepository.findByDateBetween(startDate, endDate);
        return remboursements.stream()
                .map(remboursementMapper::fromRemboursement)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTotalRemboursementsForCredit(Long creditId) {
        List<Remboursement> remboursements = remboursementRepository.findByCredit_Id(creditId);
        return remboursements.stream()
                .mapToDouble(Remboursement::getAmount)
                .sum();
    }

    @Override
    public double calculateRemainingAmountForCredit(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit == null) return 0;
        
        double totalAmount = credit.getAmount() * (1 + credit.getInterestRate() / 100);
        double paidAmount = calculateTotalRemboursementsForCredit(creditId);
        
        return totalAmount - paidAmount;
    }
} 