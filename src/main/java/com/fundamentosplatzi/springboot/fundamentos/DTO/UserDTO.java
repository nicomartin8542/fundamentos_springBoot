package com.fundamentosplatzi.springboot.fundamentos.DTO;

import java.time.LocalDate;

public class UserDTO {

    private Long id;
    private String name;
    private LocalDate brirthDate;

    public UserDTO(Long id, String name, LocalDate brirthDate) {
        this.id = id;
        this.name = name;
        this.brirthDate = brirthDate;
    }

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

    public LocalDate getBrirthDate() {
        return brirthDate;
    }

    public void setBrirthDate(LocalDate brirthDate) {
        this.brirthDate = brirthDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brirthDate=" + brirthDate +
                '}';
    }
}
