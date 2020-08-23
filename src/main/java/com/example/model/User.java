package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.example.user.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@ApiModel(description = "All details about the user.")
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have atleast 2 characters")
    @ApiModelProperty(notes = "Name should have atleast 2 characters")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birth date should be in the past")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }
}
