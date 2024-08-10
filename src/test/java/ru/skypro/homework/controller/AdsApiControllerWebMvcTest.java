package ru.skypro.homework.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.AdService;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdsApiController.class)
public class AdsApiControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;


    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setEmail("testEmail@gmail.com");
        user.setRole(Role.USER);
        Authentication authentication = Mockito.mock(Authentication.class);
        given(authentication.getName()).willReturn(user.getEmail());

        User admin = new User();
        admin.setEmail("testAdminEmail@gmail.com");
        admin.setRole(Role.ADMIN);
        Authentication authenticationTwo = Mockito.mock(Authentication.class);
        given(authenticationTwo.getName()).willReturn(admin.getEmail());
    }


    @Test
    public void testAdAds() throws Exception {
        User user = new User();
        user.setEmail("testEmail@gmail.com");
        user.setRole(Role.USER);
        Authentication authentication = Mockito.mock(Authentication.class);
        given(authentication.getName()).willReturn(user.getEmail());

        CreateOrUpdateAdDto createOrUpdateAdDto = new CreateOrUpdateAdDto();
        AdDto adDto = new AdDto();
        MockMultipartFile image = new MockMultipartFile("image", "image.png", "image/png", "test image".getBytes());

        given(adService.createAd(createOrUpdateAdDto, image, authentication));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/ads")
                        .file(image)
                        .param("properties", new ObjectMapper().writeValueAsString(adDto))
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

}
