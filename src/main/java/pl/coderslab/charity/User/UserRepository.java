package pl.coderslab.charity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.Role.Role;
import pl.coderslab.charity.User.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    User findByEmail(String email);

    List <User> findByRolesContaining(Role role);

    @Query("select u from User u where u.userName like %?1%" )
    List<User> findByUserNameContaining(String userName);

//    @Query("select u from User u where u.userName like %?1% and u.roles like ?2%" )
//    List<User> findByUserNameContaining(String userName, Role role
//    );
}
