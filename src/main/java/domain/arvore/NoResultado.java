package domain.arvore;

import domain.Alerta;
import domain.RegistroSaude;
import domain.Severidade;

import java.util.Optional;

public class NoResultado implements INoDecisao {

    private final String mensagem;
    private final Severidade severidade;

    public NoResultado(String mensagem, Severidade severidade) {
        this.mensagem = mensagem;
        this.severidade = severidade;
    }

    @Override
    public Optional<Alerta> avaliar(RegistroSaude registro) {
        return Optional.of(new Alerta(mensagem, severidade, registro.getUsuario(), registro));
    }
}
