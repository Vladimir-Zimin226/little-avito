package ru.skypro.homework.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppUserDetails(userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new));
    }

    public boolean userExists(String username) {
        User userNotExists = new User();
        User users = userRepository.findByEmail(username).orElse(userNotExists);
        return !userNotExists.equals(users);
    }

    public void createUser(RegisterDto register, String password) {
        User user = new User();
        user.setPassword(password);
        user.setPhone(register.getPhone());
        user.setEmail(register.getUsername());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setRole(register.getRole());
        userRepository.save(user);
    }
}
