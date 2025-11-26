package domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registros_saude", uniqueConstraints = @UniqueConstraint(columnNames = {"data", "usuario_id"}))
public class RegistroSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Integer passos;

    @Column(nullable = false)
    private Integer bpmMedio;

    @Column(nullable = false)
    private Double horasSono;

    @Column(nullable = false)
    private Integer kcal;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public RegistroSaude() {
    }

    public RegistroSaude(LocalDate data, Integer passos, Integer bpmMedio, Double horasSono, Integer kcal, Usuario usuario) {
        this.data = data;
        this.passos = passos;
        this.bpmMedio = bpmMedio;
        this.horasSono = horasSono;
        this.kcal = kcal;
        this.usuario = usuario;
    }

    public long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getPassos() {
        return passos;
    }

    public void setPassos(Integer passos) {
        this.passos = passos;
    }

    public Integer getBpmMedio() {
        return bpmMedio;
    }

    public void setBpmMedio(Integer bpmMedio) {
        this.bpmMedio = bpmMedio;
    }

    public Double getHorasSono() {
        return horasSono;
    }

    public void setHorasSono(Double horasSono) {
        this.horasSono = horasSono;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}