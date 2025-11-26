package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("PASSOS")
public class MetaPassos extends MetaSaude{

    private Integer alvoPassos;

    public MetaPassos() {
    }
    public MetaPassos(Usuario usuario, Integer alvoPassos) {
        super(usuario);
        this.alvoPassos = alvoPassos;
    }

   @Override
   public void verificarProgresso(List<RegistroSaude> registros) {
        if (!registros.isEmpty()) {
            RegistroSaude ultimo = registros.get(registros.size() - 1);

            if (ultimo.getPassos() >= this.alvoPassos) {
                this.setAtingida(true);
            } else {
                this.setAtingida(false);
            }
        }
   }

    public Integer getAlvoPassos() {
        return alvoPassos;
    }

    public void setAlvoPassos(Integer alvoPassos) {
        this.alvoPassos = alvoPassos;
    }
}
