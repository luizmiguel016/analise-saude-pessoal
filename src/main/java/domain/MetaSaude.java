package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "metas_saude")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_meta", discriminatorType = DiscriminatorType.STRING)
public abstract class MetaSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    private boolean atingida;

    public MetaSaude() {
    }

    public MetaSaude(Usuario usuario) {
        this.usuario = usuario;
    }

    public abstract void verificarProgresso(List<RegistroSaude> registros);

    public boolean isAtingida() {
        return atingida;
    }

    public void setAtingida(boolean atingida) {
        this.atingida = atingida;
    }
}
