package domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Severidade severidade;

    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "registro_id")
    private RegistroSaude registroRelacionado;

    public Alerta() {
    }

    public Alerta(String mensagem, Severidade severidade, LocalDateTime dataHora, Usuario usuario, RegistroSaude registroRelacionado) {
        this.mensagem = mensagem;
        this.severidade = severidade;
        this.dataHora = dataHora;
        this.usuario = usuario;
        this.registroRelacionado = registroRelacionado;
    }

    public Alerta(String mensagem, Severidade severidade, Usuario usuario, RegistroSaude registroRelacionado) {
        this.mensagem = mensagem;
        this.severidade = severidade;
        this.usuario = usuario;
        this.registroRelacionado = registroRelacionado;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Severidade getSeveridade() {
        return severidade;
    }

    public void setSeveridade(Severidade severidade) {
        this.severidade = severidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RegistroSaude getRegistroRelacionado() {
        return registroRelacionado;
    }

    public void setRegistroRelacionado(RegistroSaude registroRelacionado) {
        this.registroRelacionado = registroRelacionado;
    }
}
