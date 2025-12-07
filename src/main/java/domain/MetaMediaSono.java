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

    @Override
    public void atualizarMeta(Number novoValor) {
        double horas = novoValor.doubleValue();
        if (horas < 0) {
            throw new IllegalArgumentException("Horas de sono não pode ser negativo");
        }
        this.setHorasAlvo(horas);
    }

    @Override
    public void exibirResultado(RegistroSaude registro) {
        double sonoFeito = registro.getHorasSono();
        double alvo = getHorasAlvo();
        double restante = alvo - sonoFeito;

        System.out.println("Meta de Sono (ID: " + getId() + "):");
        System.out.println(" - Sono estipulado: " + alvo);
        System.out.println(" - Sono realizado: " + sonoFeito);
        System.out.println(" - Restantes: " + (restante > 0 ? restante : 0));
        System.out.println(" - Atingida? " + (isAtingida() ? "SIM" : "NÃO"));
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + " | Horas estipulada: " + horasAlvo;
    }
}
