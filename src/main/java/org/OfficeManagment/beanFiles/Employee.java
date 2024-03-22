package org.OfficeManagment.beanFiles;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private Long number;
    private String department;
    private String role;
    private String password;

    @OneToMany(mappedBy = "employee")
    private List<Project> project;

    public Employee() {
    }

    public Employee(String name, String email, Long number, String department, String role) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.department = department;
        this.role = role;
        this.password =email;
    }

    public Employee(int id, String name, String email, Long number, String department, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.number = number;
        this.department = department;
        this.role = role;
        this.password = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", number=" + number +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
