package cryst.blogapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Role {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



}
