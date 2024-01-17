package com.beck.springbootebookaws.db.services;

import com.beck.springbootebookaws.api.payload.client.ClientRequest;
import com.beck.springbootebookaws.api.payload.client.ClientResponse;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.exceptions.NoSuchElementException;
import com.beck.springbootebookaws.mapper.ClientEditMapper;
import com.beck.springbootebookaws.mapper.ClientViewMapper;
import com.beck.springbootebookaws.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClientViewMapper viewMapper;
    private final ClientEditMapper editMapper;

    public ClientResponse registration(ClientRequest request) {
        User user = editMapper.createUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.isActive();
        userRepository.save(user);
        log.info("The client has successfully registered: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public ClientResponse update(ClientRequest request, Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        editMapper.updateUser(user, request);
        log.info("The client has successfully updated his data: {}", user.getFirstName());
        return viewMapper.viewUser(userRepository.save(user));
    }

    public ClientResponse getById(Long id) {
        User client = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        log.info("Getting client by id: {}", client.getFirstName());
        return viewMapper.viewUser(client);
    }

    public List<ClientResponse> getAllClients() {
        log.info("Getting all clients: ");
        return viewMapper.viewClients();
    }

    public ClientResponse deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        userRepository.deleteById(id);
        log.info("Successfully deleted client by id: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public ClientResponse getClientHistory(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        log.info("Getting a client by id: {}", user.getFirstName());
        return viewMapper.viewUser(user);
    }

    public void deleteClientHistory(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(User.class, id));
        userRepository.deleteById(id);
        log.info("The client was successfully removed by ID from the database: {}", user.getFirstName());
        viewMapper.viewUser(user);
    }


}
