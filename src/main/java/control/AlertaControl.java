package control;

import domain.Alerta;
import domain.RegistroSaude;
import domain.Usuario;
import service.AlertaService;

import java.util.List;
import java.util.Optional;

public class AlertaControl {

    private final AlertaService alertaService = new AlertaService();

    public Optional<Alerta> processarRegistro(RegistroSaude registro) {
        return alertaService.processarRegistro(registro);
    }

    public List<Alerta> listar(Usuario usuario) {
        return alertaService.listarHistorico(usuario);
    }
}
