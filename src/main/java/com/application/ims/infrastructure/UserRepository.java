package com.application.ims.infrastructure;

import com.application.ims.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // querying all users by id ascending
    @Query("SELECT u FROM User u ORDER BY u.id ASC")
    List<User> findAllByIdAsc();

    Optional<User> findByEmail(String email);

}
