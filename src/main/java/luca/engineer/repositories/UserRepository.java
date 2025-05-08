package luca.engineer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import luca.engineer.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
