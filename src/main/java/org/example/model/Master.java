package org.example.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@PrimaryKeyJoinColumn(name="user_id", referencedColumnName = "id")
@DiscriminatorValue("MASTER")
public class Master extends User {
    @OneToMany(mappedBy = "master")
    private List<Course> courses = new ArrayList<>();
    @OneToMany(mappedBy = "master")
    private List<Exam> exams = new ArrayList<>();
}