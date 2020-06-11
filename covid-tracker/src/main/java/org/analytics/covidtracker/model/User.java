package org.analytics.covidtracker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.analytics.covidtracker.constants.Messages;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "test_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "email")
    @Email(message = Messages.INVALID_EMAIL)
    @NotEmpty(message = Messages.EMPTY_EMAIL)
    private String email;
    
    @Column(name = "password")
    @Size(min = 5, message = Messages.INVALID_PASSWORD)
    @NotEmpty(message = Messages.EMPTY_PASSWORD)
    private String password;
    
    @Column(name = "firstname")
    @NotEmpty(message = Messages.EMPTY_NAME)
    private String name;
    
    @Column(name = "lastname")
    @NotEmpty(message = Messages.EMPTY_LASTNAME)
    private String lastName;
    
    @Column(name = "active")
    private int active;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "test_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
