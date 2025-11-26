package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("SONO")
public class MetaMediaSono extends MetaSaude {

    private Double horasAlvo;

    public MetaMediaSono() {
    }

    public MetaMediaSono(Usuario usuario, Double horasAlvo) {
        super(usuario);
        this.horasAlvo = horasAlvo;
    }

    @Override
    public void verificarProgresso(List<RegistroSaude> registros) {
        if (registros.isEmpty()) {
            this.setAtingida(false);
            return;
        }

        double somaHoras = 0.0;
        for (RegistroSaude registro : registros) {
            somaHoras += registro.getHorasSono();
        }

        double media = somaHoras / registros.size();

        if (media >= this.horasAlvo) {
            this.setAtingida(true);
        } else {
            this.setAtingida(false);
        }
    }

    public Double getHorasAlvo() {
        return horasAlvo;
    }

    public void setHorasAlvo(Double horasAlvo) {
        this.horasAlvo = horasAlvo;
    }
}
