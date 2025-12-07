package domain.arvore;

import domain.Alerta;
import domain.RegistroSaude;
import java.util.Optional;

public interface INoDecisao {
    Optional<Alerta> avaliar(RegistroSaude registro);
}
