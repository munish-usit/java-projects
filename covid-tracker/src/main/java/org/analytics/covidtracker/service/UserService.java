package org.analytics.covidtracker.service;

import org.analytics.covidtracker.constants.RolesType;
import org.analytics.covidtracker.model.Role;
import org.analytics.covidtracker.model.User;
import org.analytics.covidtracker.repository.RoleRepository;
import org.analytics.covidtracker.repository.UserRepository;
import org.analytics.covidtracker.utils.AppLogger;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService {

	private static final Logger logger = AppLogger.getMainLogInstance(UserService.class);
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
    	logger.trace("Save user method called for user {} ",user.getEmail());
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(RolesType.ADMIN.toString());
        logger.trace("Roles {}  for user {} ",userRole.getRole().toString(),user.getEmail());
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

}