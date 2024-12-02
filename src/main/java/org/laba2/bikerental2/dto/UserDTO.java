package org.laba2.bikerental2.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;

    // Конструктор без аргументів
    public UserDTO() {
    }

    // Конструктор з аргументами
    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Геттери та сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}