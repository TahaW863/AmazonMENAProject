package com.taha.auth.auth.interfaces;

import com.taha.auth.auth.models.Customer;

public interface UserService {
    public Customer getUserById(String id);
    public Customer getUserByEmail(String email);
    public void saveUser(Customer customer);
}
