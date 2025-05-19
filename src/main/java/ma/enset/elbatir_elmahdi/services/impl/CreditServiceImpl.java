package ma.enset.elbatir_elmahdi.services.impl;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.CreditDTO;
import ma.enset.elbatir_elmahdi.dtos.CreditImmobilierDTO;
import ma.enset.elbatir_elmahdi.dtos.CreditPersonnelDTO;
import ma.enset.elbatir_elmahdi.dtos.CreditProfessionnelDTO;
import ma.enset.elbatir_elmahdi.entities.*;
import ma.enset.elbatir_elmahdi.mappers.CreditImmobilierMapper;
import ma.enset.elbatir_elmahdi.mappers.CreditMapper;
import ma.enset.elbatir_elmahdi.mappers.CreditPersonnelMapper;
import ma.enset.elbatir_elmahdi.mappers.CreditProfessionnelMapper;
import ma.enset.elbatir_elmahdi.repositories.*;
import ma.enset.elbatir_elmahdi.services.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final ClientRepository clientRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    
    private final CreditMapper creditMapper;
    private final CreditPersonnelMapper creditPersonnelMapper;
    private final CreditImmobilierMapper creditImmobilierMapper;
    private final CreditProfessionnelMapper creditProfessionnelMapper;
    
    @Override
    public CreditDTO getCredit(Long id) {
        Credit credit = creditRepository.findById(id).orElse(null);
        return creditMapper.fromCredit(credit);
    }

    @Override
    public List<CreditDTO> getAllCredits() {
        List<Credit> credits = creditRepository.findAll();
        return credits.stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<CreditDTO> getCreditsByClientId(Long clientId) {
        List<Credit> credits = creditRepository.findByClient_Id(clientId);
        return credits.stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsByStatus(CreditStatus status) {
        List<Credit> credits = creditRepository.findByStatus(status);
        return credits.stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditDTO> getCreditsBetweenDates(Date startDate, Date endDate) {
        List<Credit> credits = creditRepository.findByRequestDateBetween(startDate, endDate);
        return credits.stream()
                .map(creditMapper::fromCredit)
                .collect(Collectors.toList());
    }

    @Override
    public CreditPersonnelDTO saveCreditPersonnel(CreditPersonnelDTO creditDTO) {
        CreditPersonnel creditPersonnel = creditPersonnelMapper.fromCreditPersonnelDTO(creditDTO);
        
        // Set client reference
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId()).orElse(null);
            creditPersonnel.setClient(client);
        }
        
        CreditPersonnel savedCreditPersonnel = creditPersonnelRepository.save(creditPersonnel);
        return creditPersonnelMapper.fromCreditPersonnel(savedCreditPersonnel);
    }

    @Override
    public CreditPersonnelDTO updateCreditPersonnel(CreditPersonnelDTO creditDTO) {
        CreditPersonnel creditPersonnel = creditPersonnelMapper.fromCreditPersonnelDTO(creditDTO);
        
        // Set client reference
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId()).orElse(null);
            creditPersonnel.setClient(client);
        }
        
        CreditPersonnel updatedCreditPersonnel = creditPersonnelRepository.save(creditPersonnel);
        return creditPersonnelMapper.fromCreditPersonnel(updatedCreditPersonnel);
    }

    @Override
    public List<CreditPersonnelDTO> getAllCreditPersonnel() {
        List<CreditPersonnel> credits = creditPersonnelRepository.findAll();
        return credits.stream()
                .map(creditPersonnelMapper::fromCreditPersonnel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditPersonnelDTO> getCreditPersonnelByMotif(String motif) {
        List<CreditPersonnel> credits = creditPersonnelRepository.findByMotifContaining(motif);
        return credits.stream()
                .map(creditPersonnelMapper::fromCreditPersonnel)
                .collect(Collectors.toList());
    }

    @Override
    public CreditImmobilierDTO saveCreditImmobilier(CreditImmobilierDTO creditDTO) {
        CreditImmobilier creditImmobilier = creditImmobilierMapper.fromCreditImmobilierDTO(creditDTO);
        
        // Set client reference
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId()).orElse(null);
            creditImmobilier.setClient(client);
        }
        
        CreditImmobilier savedCreditImmobilier = creditImmobilierRepository.save(creditImmobilier);
        return creditImmobilierMapper.fromCreditImmobilier(savedCreditImmobilier);
    }

    @Override
    public CreditImmobilierDTO updateCreditImmobilier(CreditImmobilierDTO creditDTO) {
        CreditImmobilier creditImmobilier = creditImmobilierMapper.fromCreditImmobilierDTO(creditDTO);
        
        // Set client reference
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId()).orElse(null);
            creditImmobilier.setClient(client);
        }
        
        CreditImmobilier updatedCreditImmobilier = creditImmobilierRepository.save(creditImmobilier);
        return creditImmobilierMapper.fromCreditImmobilier(updatedCreditImmobilier);
    }

    @Override
    public List<CreditImmobilierDTO> getAllCreditImmobilier() {
        List<CreditImmobilier> credits = creditImmobilierRepository.findAll();
        return credits.stream()
                .map(creditImmobilierMapper::fromCreditImmobilier)
                .collect(Collectors.toList());
    }

    @Override
    public CreditProfessionnelDTO saveCreditProfessionnel(CreditProfessionnelDTO creditDTO) {
        CreditProfessionnel creditProfessionnel = creditProfessionnelMapper.fromCreditProfessionnelDTO(creditDTO);
        
        // Set client reference
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId()).orElse(null);
            creditProfessionnel.setClient(client);
        }
        
        CreditProfessionnel savedCreditProfessionnel = creditProfessionnelRepository.save(creditProfessionnel);
        return creditProfessionnelMapper.fromCreditProfessionnel(savedCreditProfessionnel);
    }

    @Override
    public CreditProfessionnelDTO updateCreditProfessionnel(CreditProfessionnelDTO creditDTO) {
        CreditProfessionnel creditProfessionnel = creditProfessionnelMapper.fromCreditProfessionnelDTO(creditDTO);
        
        // Set client reference
        if (creditDTO.getClientId() != null) {
            Client client = clientRepository.findById(creditDTO.getClientId()).orElse(null);
            creditProfessionnel.setClient(client);
        }
        
        CreditProfessionnel updatedCreditProfessionnel = creditProfessionnelRepository.save(creditProfessionnel);
        return creditProfessionnelMapper.fromCreditProfessionnel(updatedCreditProfessionnel);
    }

    @Override
    public List<CreditProfessionnelDTO> getAllCreditProfessionnel() {
        List<CreditProfessionnel> credits = creditProfessionnelRepository.findAll();
        return credits.stream()
                .map(creditProfessionnelMapper::fromCreditProfessionnel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CreditProfessionnelDTO> getCreditProfessionnelByRaisonSociale(String raisonSociale) {
        List<CreditProfessionnel> credits = creditProfessionnelRepository.findByRaisonSocialeContaining(raisonSociale);
        return credits.stream()
                .map(creditProfessionnelMapper::fromCreditProfessionnel)
                .collect(Collectors.toList());
    }

    @Override
    public CreditDTO approveCreditRequest(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit != null) {
            credit.setStatus(CreditStatus.ACCEPTE);
            credit.setAcceptanceDate(new Date());
            Credit updatedCredit = creditRepository.save(credit);
            return creditMapper.fromCredit(updatedCredit);
        }
        return null;
    }

    @Override
    public CreditDTO rejectCreditRequest(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit != null) {
            credit.setStatus(CreditStatus.REJETE);
            Credit updatedCredit = creditRepository.save(credit);
            return creditMapper.fromCredit(updatedCredit);
        }
        return null;
    }

    @Override
    public double calculateTotalInterest(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit != null) {
            return credit.getAmount() * credit.getInterestRate() / 100 * credit.getDuration() / 12;
        }
        return 0;
    }

    @Override
    public double calculateMonthlyPayment(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit != null) {
            double principal = credit.getAmount();
            double monthlyRate = credit.getInterestRate() / 100 / 12;
            int numberOfPayments = credit.getDuration();
            
            // Monthly payment formula: P * r * (1+r)^n / ((1+r)^n - 1)
            double numerator = monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments);
            double denominator = Math.pow(1 + monthlyRate, numberOfPayments) - 1;
            
            if (denominator != 0) {
                return principal * numerator / denominator;
            }
            return principal / numberOfPayments;
        }
        return 0;
    }
} 