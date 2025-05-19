package ma.enset.elbatir_elmahdi;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.entities.*;
import ma.enset.elbatir_elmahdi.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository; 
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final RemboursementRepository remboursementRepository;
    private final Random random = new Random();

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Initializing data...");
        
        // Create clients
        Stream.of("Client1", "Client2", "Client3").forEach(name -> {
            Client client = new Client();
            client.setName(name);
            client.setEmail(name.toLowerCase() + "@gmail.com");
            clientRepository.save(client);
        });
        
        // Create credit personnel
        List<Client> clients = clientRepository.findAll();
        String[] motifs = {"Achat voiture", "Études", "Travaux"};
        
        for (int i = 0; i < 3; i++) {
            Client client = clients.get(random.nextInt(clients.size()));
            CreditPersonnel creditPersonnel = new CreditPersonnel();
            creditPersonnel.setClient(client);
            creditPersonnel.setRequestDate(new Date());
            creditPersonnel.setStatus(CreditStatus.EN_COURS);
            creditPersonnel.setAmount(10000.0 + random.nextInt(90000));
            creditPersonnel.setDuration(12 + random.nextInt(48));
            creditPersonnel.setInterestRate(4.0 + random.nextDouble() * 2.0);
            creditPersonnel.setMotif(motifs[random.nextInt(motifs.length)]);
            creditPersonnelRepository.save(creditPersonnel);
            
            // Create remboursements for each credit
            createRemboursements(creditPersonnel);
        }
        
        // Create credit immobilier
        for (int i = 0; i < 2; i++) {
            Client client = clients.get(random.nextInt(clients.size()));
            CreditImmobilier creditImmobilier = new CreditImmobilier();
            creditImmobilier.setClient(client);
            creditImmobilier.setRequestDate(new Date());
            creditImmobilier.setStatus(CreditStatus.ACCEPTE);
            creditImmobilier.setAcceptanceDate(new Date());
            creditImmobilier.setAmount(100000.0 + random.nextInt(900000));
            creditImmobilier.setDuration(120 + random.nextInt(120));
            creditImmobilier.setInterestRate(3.0 + random.nextDouble() * 1.5);
            
            PropertyType[] propertyTypes = PropertyType.values();
            creditImmobilier.setPropertyType(propertyTypes[random.nextInt(propertyTypes.length)]);
            
            creditImmobilierRepository.save(creditImmobilier);
            
            // Create remboursements for each credit
            createRemboursements(creditImmobilier);
        }
        
        // Create credit professionnel
        String[] raisonsSociales = {"Société A", "Entreprise B", "Startup C"};
        for (int i = 0; i < 2; i++) {
            Client client = clients.get(random.nextInt(clients.size()));
            CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
            creditProfessionnel.setClient(client);
            creditProfessionnel.setRequestDate(new Date());
            creditProfessionnel.setStatus(i % 2 == 0 ? CreditStatus.ACCEPTE : CreditStatus.REJETE);
            if (creditProfessionnel.getStatus() == CreditStatus.ACCEPTE) {
                creditProfessionnel.setAcceptanceDate(new Date());
            }
            creditProfessionnel.setAmount(50000.0 + random.nextInt(450000));
            creditProfessionnel.setDuration(24 + random.nextInt(96));
            creditProfessionnel.setInterestRate(5.0 + random.nextDouble() * 3.0);
            creditProfessionnel.setMotif("Investissement matériel");
            creditProfessionnel.setRaisonSociale(raisonsSociales[random.nextInt(raisonsSociales.length)]);
            
            creditProfessionnelRepository.save(creditProfessionnel);
            
            // Create remboursements for accepted credits
            if (creditProfessionnel.getStatus() == CreditStatus.ACCEPTE) {
                createRemboursements(creditProfessionnel);
            }
        }
        
        System.out.println("Data initialization completed.");
    }
    
    private void createRemboursements(Credit credit) {
        int nbRemboursements = random.nextInt(5) + 1; // 1 to 5 remboursements
        
        for (int i = 0; i < nbRemboursements; i++) {
            Remboursement remboursement = new Remboursement();
            remboursement.setCredit(credit);
            remboursement.setDate(new Date());
            
            // Determine remboursement type
            RemboursementType[] types = RemboursementType.values();
            RemboursementType type = types[random.nextInt(types.length)];
            remboursement.setType(type);
            
            // Calculate remboursement amount
            double monthlyPayment = credit.getAmount() / credit.getDuration();
            if (type == RemboursementType.MENSUALITE) {
                remboursement.setAmount(monthlyPayment);
            } else {
                // Remboursement anticipé: larger amount
                remboursement.setAmount(monthlyPayment * (2 + random.nextInt(4)));
            }
            
            remboursementRepository.save(remboursement);
        }
    }
} 