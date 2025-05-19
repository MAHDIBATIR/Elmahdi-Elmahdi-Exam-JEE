package ma.enset.elbatir_elmahdi.mappers;

import ma.enset.elbatir_elmahdi.dtos.ClientDTO;
import ma.enset.elbatir_elmahdi.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    
    public ClientDTO fromClient(Client client) {
        if (client == null) return null;
        
        return new ClientDTO(
                client.getId(),
                client.getName(),
                client.getEmail()
        );
    }
    
    public Client fromClientDTO(ClientDTO clientDTO) {
        if (clientDTO == null) return null;
        
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        
        return client;
    }
} 