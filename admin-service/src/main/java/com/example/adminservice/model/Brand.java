package com.example.adminservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Model> models;

    public Brand(String name) {
        this.name = name;
        this.models = new HashSet<>();
    }

    public void removeModel(String name){
        for(Model m : this.models){
            if(m.getName().equals(name)){
                this.models.remove(m);
                return;
            }
        }
    }
}
