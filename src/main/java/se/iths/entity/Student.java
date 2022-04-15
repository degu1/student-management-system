package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "student.getAllByLastName", query = "SELECT s FROM Student s where s.lastName = :lastName")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Column(unique = true)
    private String email;
    private String phoneNumber;

    @ManyToMany(targetEntity = Subject.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Subject> subjects = new ArrayList<>();

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.addStudent(this);
    }

    public void removeSubject(Subject subject) {
        this.subjects.remove(subject);
        subject.removeStudent(this);
    }

    public void removeAllSubjects() {
        subjects.forEach(s -> s.removeStudent(this));
        subjects.clear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> subjects() {
        return this.subjects;
    }
}
