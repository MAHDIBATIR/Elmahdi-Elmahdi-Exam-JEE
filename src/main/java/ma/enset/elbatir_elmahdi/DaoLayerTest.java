package ma.enset.elbatir_elmahdi;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.entities.Client;
import ma.enset.elbatir_elmahdi.entities.Credit;
import ma.enset.elbatir_elmahdi.repositories.ClientRepository;
import ma.enset.elbatir_elmahdi.repositories.CreditRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(value = 2)
public class DaoLayerTest implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n====== Testing DAO Layer ======");
        
        // Test querying all clients
        System.out.println("Clients in database:");
        List<Client> clients = clientRepository.findAll();
        clients.forEach(client -> {
            System.out.println("- " + client.getName() + " (" + client.getEmail() + ")");
        });
        
        // Test querying credits per client
        if (!clients.isEmpty()) {
            Client firstClient = clients.get(0);
            System.out.println("\nCredits for client " + firstClient.getName() + ":");
            List<Credit> credits = creditRepository.findByClient_Id(firstClient.getId());
            credits.forEach(credit -> {
                System.out.println("- Credit ID: " + credit.getId() + 
                        ", Amount: " + credit.getAmount() + 
                        ", Status: " + credit.getStatus());
            });
        }
        
        System.out.println("====== DAO Layer Test Completed ======\n");
    }
} 