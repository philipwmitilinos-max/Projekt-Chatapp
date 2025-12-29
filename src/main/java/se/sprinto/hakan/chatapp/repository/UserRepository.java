package se.sprinto.hakan.chatapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import se.sprinto.hakan.chatapp.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    User save(User user);
    @Query("""
        SELECT u FROM User u
        JOIN FETCH u.messages
        WHERE u.username = :username AND u.password = :password
""")
    User findByUsernameAndPassword(String username, String password);
//    List<User> findAll();
}

