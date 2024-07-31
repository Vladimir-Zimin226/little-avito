package ru.skypro.homework.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.SecurityDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@Service
public class OurSecutiryDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private OurSecurityDetails ourSecurityDetails;


    public OurSecutiryDetailsService() {

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SecurityDto> securityDto = userRepository.findUserByEmailIgnoreCase(username)
                .map(userMapper::toSecurityDto);
        return securityDto.map(OurSecurityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

    }
}
