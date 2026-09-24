package com.first_springboot.demo.dto;

import com.first_springboot.demo.model.Player;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    //validation in serviceImpl
    private Long id;

//@NotNull
//@Size(min = 2,max = 20)
//@Pattern(regexp = "[A-Z][a-z]+",  message = "First letter must be capital and the rest must be small" )
//@NotEmpty
@NotBlank(message = "invaled name")
    private String name;

@Min(18)
@Max(100)
    private Integer number;

    private Double salary;

    private String details;

    private Long count;

    @Email
    private String email;






    public PlayerDto(Long id, String name, Integer number, Double salary) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.salary = salary;
    }

    public PlayerDto(String name, Integer number, Double salary) {
        this.name = name;
        this.number = number;
        this.salary = salary;

    }




    public PlayerDto toDto(Player player){
        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getNumber(),
                player.getSalary()
        );
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", salary=" + salary +
                '}';
    }
}
