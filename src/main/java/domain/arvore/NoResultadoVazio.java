package domain.arvore;

import domain.Alerta;
import domain.RegistroSaude;

import java.util.Optional;

public class NoResultadoVazio implements INoDecisao {

    @Override
    public Optional<Alerta> avaliar(RegistroSaude registro) {
        return Optional.empty();
    }
}
