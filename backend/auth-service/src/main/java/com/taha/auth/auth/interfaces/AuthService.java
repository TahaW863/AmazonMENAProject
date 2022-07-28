package com.taha.auth.auth.interfaces;



import com.taha.auth.auth.enums.Roles;

import java.util.Optional;

public interface AuthService {
    Optional<Boolean> isAuthenticated(String username, String password);

    Optional<Boolean> isUserWithRole(String username, Roles role);

    Optional<Void> addRole(Roles role);

    Optional<Void> removeRole(Roles role);

}
