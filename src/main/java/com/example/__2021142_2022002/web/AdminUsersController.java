package com.example.__2021142_2022002.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;  // <-- import
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Admin")
public class AdminUsersController {

    private final InMemoryUserDetailsManager users;

    public AdminUsersController(InMemoryUserDetailsManager users) {
        this.users = users;
    }

    @GetMapping("/users")
    public List<Map<String,Object>> listUsers() {
        List<Map<String,Object>> out = new ArrayList<>();
        for (String u : List.of("admin","alice","bob")) {
            var ud = users.loadUserByUsername(u); // τύπος: UserDetails
            out.add(Map.of(
                    "username", ud.getUsername(),
                    "roles", ud.getAuthorities().stream().map(Object::toString).toList(),
                    "enabled", ud.isEnabled()
            ));
        }
        return out;
    }
}