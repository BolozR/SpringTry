package ru.springtry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springtry.models.User;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByFirstName(String firstName);
}
