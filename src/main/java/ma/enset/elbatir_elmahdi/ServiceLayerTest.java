package ma.enset.elbatir_elmahdi;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.ClientDTO;
import ma.enset.elbatir_elmahdi.dtos.CreditDTO;
import ma.enset.elbatir_elmahdi.entities.CreditStatus;
import ma.enset.elbatir_elmahdi.services.ClientService;
import ma.enset.elbatir_elmahdi.services.CreditService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(value = 3)
public class ServiceLayerTest implements CommandLineRunner {
    private final ClientService clientService;
    private final CreditService creditService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n====== Testing Service Layer ======");
        
        // Test client service
        System.out.println("Clients via Service Layer:");
        List<ClientDTO> clients = clientService.getAllClients();
        clients.forEach(client -> {
            System.out.println("- " + client.getName() + " (" + client.getEmail() + ")");
        });
        
        // Test credit service
        if (!clients.isEmpty()) {
            ClientDTO firstClient = clients.get(0);
            System.out.println("\nCredits for client " + firstClient.getName() + " via Service Layer:");
            List<CreditDTO> credits = creditService.getCreditsByClientId(firstClient.getId());
            credits.forEach(credit -> {
                System.out.println("- Credit ID: " + credit.getId() + 
                        ", Amount: " + credit.getAmount() + 
                        ", Status: " + credit.getStatus());
                
                if (credit.getStatus() == CreditStatus.EN_COURS) {
                    System.out.println("  Monthly payment: " + creditService.calculateMonthlyPayment(credit.getId()));
                    System.out.println("  Total interest: " + creditService.calculateTotalInterest(credit.getId()));
                }
            });
        }
        
        System.out.println("====== Service Layer Test Completed ======\n");
    }
} 