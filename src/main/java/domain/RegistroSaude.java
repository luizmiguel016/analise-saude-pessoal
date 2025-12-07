package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "registros_saude", uniqueConstraints = @UniqueConstraint(columnNames = {"data", "usuario_id"}))
public class RegistroSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        DateTimeFormatter br = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return "ID: " + id
                + " | Data: " + data.format(br)
                + " | Passos: " + passos
                + " | BPM: " + bpmMedio
                + " | Sono: " + horasSono
                + " | Kcal: " + kcal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroSaude)) return false;
        RegistroSaude other = (RegistroSaude) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}