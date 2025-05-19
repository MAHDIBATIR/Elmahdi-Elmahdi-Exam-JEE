package ma.enset.elbatir_elmahdi.web;

import lombok.RequiredArgsConstructor;
import ma.enset.elbatir_elmahdi.dtos.ClientDTO;
import ma.enset.elbatir_elmahdi.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ClientRestController {
    private final ClientService clientService;

    @GetMapping
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.getClient(id);
        if (clientDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClientDTO> getClientByEmail(@PathVariable String email) {
        ClientDTO clientDTO = clientService.getClientByEmail(email);
        if (clientDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientDTO);
    }

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        ClientDTO updatedClient = clientService.updateClient(clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
} 