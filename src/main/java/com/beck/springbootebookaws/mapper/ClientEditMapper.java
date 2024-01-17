package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.client.ClientRequest;
import com.beck.springbootebookaws.db.entity.Role;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientEditMapper {

    private RoleRepository roleRepository;

    public User createUser(ClientRequest userRequest){
        if (userRequest==null){
            return null;
        }
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(user.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);

        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById(3L).get();
        roles.add(role);
        user.setRoles(roles);

        return user;
    }

    public User updateUser(User user, ClientRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(user.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        return user;
    }
}
