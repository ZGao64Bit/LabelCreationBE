package com.zack.labelcreation.repositories;

import com.zack.labelcreation.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Authority findAuthorityByUsername(String username);
}
