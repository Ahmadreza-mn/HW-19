package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.model.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Role extends BaseEntity<Long> {
    @Setter
    @Getter
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();


    @Setter
    @Getter
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        public Role() {}

        public Role(String name) {
            this.name = name;
        }

}
