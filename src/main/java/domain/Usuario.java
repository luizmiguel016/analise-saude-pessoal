package domain;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private Integer bpmMinAlerta;
    @Column(nullable = false)
    private Integer bpmMaxAlerta;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, String cpf, Integer bpmMin, Integer bpmMax) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.bpmMinAlerta = bpmMin;
        this.bpmMaxAlerta = bpmMax;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getBpmMinAlerta() {
        return bpmMinAlerta;
    }

    public void setBpmMinAlerta(Integer i) {
        this.bpmMinAlerta = i;
    }

    public Integer getBpmMaxAlerta() {
        return bpmMaxAlerta;
    }

    public void setBpmMaxAlerta(Integer i) {
        this.bpmMaxAlerta = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario other = (Usuario) o;
        return id != null && id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}