package com.codefellowship.codefellowship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByUsername(String username);
}
