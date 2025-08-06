package whsmumu.github.frequencyMonitoring.model;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import whsmumu.github.frequencyMonitoring.enums.Genero;

@Data
@Entity
@NoArgsConstructor
@Table(name = "lideres")
@EntityListeners(AuditingEntityListener.class)
public class Lider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "number_phone", unique = true, nullable = false)
    private String phone;

    @OneToOne(mappedBy = "lider")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Celula celula;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Genero genero;

    @CreatedDate
    @Column(name = "created_at")
    private OffsetDateTime dataCriacao;

}
