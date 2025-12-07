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

    @Override
    public void atualizarMeta(Number novoValor) {
        int passos = novoValor.intValue();
        if (passos < 0) {
            throw new IllegalArgumentException("Passos não pode ser negativo.");
        }
        this.setAlvoPassos(passos);
    }

    @Override
    public void exibirResultado(RegistroSaude registro) {
        int passosFeitos = registro.getPassos();
        int alvo = getAlvoPassos();
        int restante = alvo - passosFeitos;

        System.out.println("Meta de Passos (ID: " + getId() + "):");
        System.out.println(" - Passos estipulado: " + alvo);
        System.out.println(" - Passos feitos: " + passosFeitos);
        System.out.println(" - Restantes: " + (restante > 0 ? restante : 0));
        System.out.println(" - Atingida? " + (isAtingida() ? "SIM" : "NÃO"));
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + " | Passos estipulado: " + alvoPassos;
    }


}
