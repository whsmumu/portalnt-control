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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import whsmumu.github.frequencyMonitoring.enums.CategoriaMembro;
import whsmumu.github.frequencyMonitoring.enums.Genero;

@Entity
@Data
@NoArgsConstructor
@Table(name = "membros")
@EntityListeners(AuditingEntityListener.class)
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "number_phone", unique = true, nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_membro", nullable = false)
    private CategoriaMembro categoriaMembro;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Genero genero;

    
    @ManyToOne
    @JoinColumn(name = "celula_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Celula celula;

    @CreatedDate
    @Column(name = "created_at")
    private OffsetDateTime dataCriacao;

}
