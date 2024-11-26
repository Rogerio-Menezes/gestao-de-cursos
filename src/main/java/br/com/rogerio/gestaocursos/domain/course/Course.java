package br.com.rogerio.gestaocursos.domain.course;

import br.com.rogerio.gestaocursos.domain.course.dto.CourseDTO;
import br.com.rogerio.gestaocursos.domain.course.utils.CourseStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder()
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String category;

    private String shift;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CourseStatus situation;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Course(CourseDTO dto){
        this.name = dto.name();
        this.category = dto.category();
        this.shift = dto.shift();
        this.situation = CourseStatus.ACTIVE;

    }



}
