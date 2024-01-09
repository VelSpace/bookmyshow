package com.vel.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseModel{

    private String name;

    private String email;

    private String password;

    @OneToMany
    private List<Booking> bookings;
}
