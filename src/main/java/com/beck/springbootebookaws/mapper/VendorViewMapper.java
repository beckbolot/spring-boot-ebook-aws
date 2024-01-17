package com.beck.springbootebookaws.mapper;

import com.beck.springbootebookaws.api.payload.vendor.VendorResponse;
import com.beck.springbootebookaws.db.entity.Role;
import com.beck.springbootebookaws.db.entity.User;
import com.beck.springbootebookaws.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VendorViewMapper {

    private final RoleRepository roleRepository;

    public VendorResponse viewVendor(User vendor) {
        if (vendor == null) {
            return null;
        }
        VendorResponse response = new VendorResponse();
        if (vendor.getId() != null) {
            response.setId(vendor.getId());
        }
        response.setFirstName(vendor.getFirstName());
        response.setLastName(vendor.getLastName());
        response.setPhoneNumber(vendor.getPhoneNumber());
        response.setEmail(vendor.getEmail());
        response.setCreatedAt(vendor.getCreatedAt());
        response.setIsActive(true);
        response.setBookList(vendor.getBooks());
        response.setPromoCodes(vendor.getPromoCodes());
        return response;
    }

    public List<VendorResponse> viewVendors() {
        List<VendorResponse> vendorUsers = new ArrayList<>();
        Role role = roleRepository.findById(2L).get();
        for (User user : role.getUsers()) {
            vendorUsers.add(viewVendor(user));
        }
        return vendorUsers;
    }

    public VendorResponse viewVendorById(User vendor) {
        VendorResponse response = new VendorResponse();
        response.setFirstName(vendor.getFirstName());
        response.setLastName(vendor.getLastName());
        response.setPhoneNumber(vendor.getPhoneNumber());
        response.setEmail(vendor.getEmail());
        response.setCreatedAt(vendor.getCreatedAt());
        return response;
    }


}
