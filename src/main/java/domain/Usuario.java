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
    private String senha; // Adicionado para o login

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    private Integer bpmMinAlerta;
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

    // Getters e Setters (Gere via IDE: Alt+Insert ou botão direito -> Generate)
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public Integer getBpmMinAlerta() { return bpmMinAlerta; }
    public void setBpmMinAlerta(Integer i) { this.bpmMinAlerta = i; }
    public Integer getBpmMaxAlerta() { return bpmMaxAlerta; }
    public void setBpmMaxAlerta(Integer i) { this.bpmMaxAlerta = i; }
}