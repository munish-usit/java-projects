package org.analytics.covidtracker.testcases;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import org.analytics.covidtracker.model.Role;
import org.analytics.covidtracker.model.User;
import org.analytics.covidtracker.repository.RoleRepository;
import org.analytics.covidtracker.repository.UserRepository;
import org.analytics.covidtracker.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;
    private Role role;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserService(mockUserRepository,
                                               mockRoleRepository,
                                               mockBCryptPasswordEncoder);
        user = User.builder()
                .id(1)
                .name("munish")
                .lastName("sharma")
                .email("test@test.com")
                .build();
        role = Role.builder()
        		.id(1)
        		.role("ADMIN")
        		.build();

        Mockito.when(mockRoleRepository.findByRole(anyString()))
        		.thenReturn(role);
        
        Mockito.when(mockUserRepository.save(any()))
                .thenReturn(user);
        Mockito.when(mockUserRepository.findByEmail(anyString()))
                .thenReturn(user);
   
    }

    @Test
    public void testFindUserByEmail() {
        
        final String email = "test@test.com";

        final User result = userServiceUnderTest.findUserByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
       
        final String email = "test@test.com";

        User result = userServiceUnderTest.saveUser(User.builder().build());

        assertEquals(email, result.getEmail());
    }
}
