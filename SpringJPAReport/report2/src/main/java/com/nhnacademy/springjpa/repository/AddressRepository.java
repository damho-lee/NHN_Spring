package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAddressesByUser_UserId(String userId);

    int countByAddressId(Integer addressId);
}
