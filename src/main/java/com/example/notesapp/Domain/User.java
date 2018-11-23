package com.example.notesapp.Domain;

import com.example.notesapp.Controllers.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class User extends AuditModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "firstName can't be empty")
    @Pattern(regexp = "^([a-zA-Z]{2,})$", message = "Name must be more than 2 letters")
    @Column(nullable = false)
    private String firstName;

    @NotEmpty(message = "LastName can't be empty")
    @Pattern(regexp = "^([a-zA-Z]{2,})$", message = "Name must be more than 2 letters")
    @Column(nullable = false)
    private String lastName;

    @NotEmpty(message = "UserName can't be empty")
    @Pattern(regexp = "^([a-zA-Z]{2,})$", message = "Name must be more than 2 letters")
    @Column(nullable = false)
    private String userName;

    @NotEmpty(message = "DateOfBirth can't be left empty")
    @Column(nullable = false)
    private String dateOfBirth;

    @Email
    @NotEmpty(message = "Email can't be empty")
    @Column(nullable = false)
    private String email;

    @NotEmpty(message = "Phone number can't be empty")
    @Column(nullable = false)
    private String phoneNumber;


    private String profileImage;


    @Size(min = 4)
    @NotEmpty(message = "Please Set up Password")
    @Column(nullable = false)
    private String password;


    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL
    )
    private Set<Note> note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role role;


    public User() {
    }

    public User(String firstName,String lastName,String userName,String dateOfBirth,String email,String phoneNumber, String profileImage,String password, Set<Note> note, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.password = password;
        this.note = note;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Note> getNote() {
        return note;
    }

    public void setNote(Set<Note> note) {
        this.note = note;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUserName(), getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
