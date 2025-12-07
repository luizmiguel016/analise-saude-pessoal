package domain;

import domain.arvore.INoDecisao;
import domain.arvore.NoDeDecisao;
import domain.arvore.NoResultado;
import domain.arvore.NoResultadoVazio;

import java.util.Optional;

public class ArvoreDeDecisaoAlerta {

    private final INoDecisao raiz;

    public ArvoreDeDecisaoAlerta() {
        this.raiz = construirArvore();
    }

    private INoDecisao construirArvore() {
        INoDecisao alertaCriticoBPM = new NoResultado(
                "BPM Crítico! Procure um médico.",
                Severidade.CRITICA
        );

        INoDecisao alertaAltoBPM = new NoResultado(
                "BPM acima do máximo. Procure um médico",
                Severidade.ALTA
        );

        INoDecisao alertaBaixoSono = new NoResultado(
                "Sono insuficiente. Descanse mais.",
                Severidade.MEDIA
        );

        INoDecisao semProblemas = new NoResultadoVazio();

        INoDecisao decisaoSono = new NoDeDecisao(
                (registro) -> registro.getHorasSono() != null &&
                        registro.getHorasSono() < 7,
                alertaBaixoSono,
                semProblemas
        );

        INoDecisao decisaoBPMAlto = new NoDeDecisao(
                (registro) -> registro.getBpmMedio() != null &&
                        registro.getBpmMedio() > registro.getUsuario().getBpmMaxAlerta(),
                alertaAltoBPM,
                decisaoSono
        );

        return new NoDeDecisao(
                (registro) -> registro.getBpmMedio() != null &&
                        registro.getBpmMedio() > 150,
                alertaCriticoBPM,
                decisaoBPMAlto
        );
    }

    public Optional<Alerta> avaliar(RegistroSaude registro) {
        return raiz.avaliar(registro);
    }
}