package pl.coderslab.charity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.Role.Role;
import pl.coderslab.charity.Role.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllAdmins() {
        Role role = roleRepository.findByRole("ADMIN");
        return userRepository.findByRolesContaining(role);
    }

    public List<User> getAllUsers() {
        Role role = roleRepository.findByRole("USER");
        return userRepository.findByRolesContaining(role);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<User> findByUserNameContaining(String userName) {
        return userRepository.findByUserNameContaining(userName);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User blockUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(false);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

        return userRepository.save(user);
    }

    public User saveAdmin(User admin) {
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
        admin.setActive(true);
        Role userRole = roleRepository.findByRole("ADMIN");
        admin.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(admin);
    }

    public long countAllUsers() {
        return userRepository.count();
    }

}
