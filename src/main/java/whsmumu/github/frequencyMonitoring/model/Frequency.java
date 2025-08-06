package whsmumu.github.frequencyMonitoring.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "frequencia")
@EntityListeners(AuditingEntityListener.class)
public class Frequency {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = true)
    private LocalDate data;

    @Column(nullable = true)
    private Integer quantidadeMembrosHomem;

    @Column(nullable = true)
    private Integer quantidadeMembrosMulheres;

    @Column(nullable = true)
    private Integer quantidadeVisitantesHomem;

    @Column(nullable = true)
    private Integer quantidadeVisitantesMulher;

    @Column(nullable = true)
    private Integer quantidadeKids;

    @Column(nullable = true)
    private Integer quantidadeBaby;

    @CreatedDate
    private LocalDateTime dataFrequenciaCriacao;

    @LastModifiedDate
    private LocalDateTime dataUltimaAtualizacao;

}
