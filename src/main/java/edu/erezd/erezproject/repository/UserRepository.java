package edu.erezd.erezproject.repository;

import edu.erezd.erezproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}