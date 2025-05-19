package ma.enset.elbatir_elmahdi.services;

import ma.enset.elbatir_elmahdi.dtos.ClientDTO;

import java.util.List;

public interface ClientService {
    
    // CRUD operations
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO getClient(Long id);
    List<ClientDTO> getAllClients();
    ClientDTO updateClient(ClientDTO clientDTO);
    void deleteClient(Long id);
    
    // Additional operations
    ClientDTO getClientByEmail(String email);
} 