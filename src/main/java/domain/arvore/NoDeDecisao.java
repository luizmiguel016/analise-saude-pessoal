package domain.arvore;

import domain.Alerta;
import domain.RegistroSaude;

import java.util.Optional;
import java.util.function.Predicate;

public class NoDeDecisao implements INoDecisao {

    private final Predicate<RegistroSaude> condicao;
    private final INoDecisao seVerdadeiro;
    private final INoDecisao seFalso;

    public NoDeDecisao(Predicate<RegistroSaude> condicao, INoDecisao seVerdadeiro, INoDecisao seFalso) {
        this.condicao = condicao;
        this.seVerdadeiro = seVerdadeiro;
        this.seFalso = seFalso;
    }

    @Override
    public Optional<Alerta> avaliar(RegistroSaude registro) {
        if (condicao.test(registro)) {
            return seVerdadeiro.avaliar(registro);
        } else {
            return seFalso.avaliar(registro);
        }
    }

}
