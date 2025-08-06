package whsmumu.github.frequencyMonitoring.model;

import java.time.OffsetDateTime;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import whsmumu.github.frequencyMonitoring.enums.CategoriaCelula;

@Entity
@Data
@NoArgsConstructor
@Table(name = "celulas")
@EntityListeners(AuditingEntityListener.class)
public class Celula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String bairro;

    @OneToOne
    @JoinColumn(name = "lider_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude 
    private Lider lider;

    @OneToMany(mappedBy = "celula")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Membro> membros;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_celula", nullable = false)
    private CategoriaCelula categoriaCelula;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "dia_celula", nullable = false)
    private String diaCelula;

}
