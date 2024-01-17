package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.client.ClientResponse;
import com.beck.springbootebookaws.db.entity.Role;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientViewMapper {

    private final RoleRepository roleRepository;

    public ClientResponse viewUser(User user) {
        if (user == null) {
            return null;
        }
        ClientResponse response = new ClientResponse();
        if (user.getId() != null) {
            response.setId(Long.valueOf(user.getId()));
        }
        response.setFirstName(user.getFirstName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setOperationList(user.getHistoryOperations());
        response.setActive(true);
        return response;
    }

    public List<ClientResponse> viewClients() {
        List<ClientResponse> clients = new ArrayList<>();
        Role role = roleRepository.findById(3L).get();
        for (User client : role.getUsers()) {
            clients.add(viewUser(client));
        }
        return clients;
    }
}
