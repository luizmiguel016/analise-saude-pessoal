# 📌 Sistema de Análise de Dados de Saúde Pessoal  
**Java • POO • JPA (Hibernate) • MySQL • EDs Próprias (Fila + Lista Encadeada) • Árvore de Decisão POO**

---

## 1. Visão Geral

O **Sistema de Análise de Dados de Saúde Pessoal** permite que usuários:

- registrem métricas diárias de saúde (passos, batimentos, sono e calorias),
- acompanhem metas personalizadas,
- recebam alertas automáticos baseados em uma **Árvore de Decisão orientada a objetos**.

O sistema demonstra conceitos avançados de POO:

- Herança  
- Polimorfismo  
- Encapsulamento  
- Composição  
- Padrão **Composite**  
- Estruturas de dados próprias implementadas manualmente

**Stack utilizada:**  
`Java` • `JPA (Hibernate)` • `MySQL 8` • `Maven` • `phpMyAdmin`

---

## 2. Estrutura do Projeto

src/main/java/
├── app/ → Classe Main (execução e testes)
├── domain/ → Entidades e Regras do Domínio
│ ├── arvore/ → Árvore de Decisão (Composite)
│ ├── meta/ → Metas de Saúde (Herança)
│ └── ... → Usuário, RegistroSaude, Alerta, etc.
├── dao/ → Persistência JPA (DAOs)
├── service/ → Regras de Negócio
└── util/estruturadados/ → Estruturas de Dados próprias

## 3. Objetivos do Projeto

- Aplicar POO de forma clara, correta e idiomática (encapsulamento, coesão, polimorfismo).
- Implementar regras reais usando **Árvore de Decisão POO**, eliminando `if/else`.
- Modelar entidades e persistência com **JPA/Hibernate**.
- Criar e usar **estruturas de dados próprias** (Fila e Lista Encadeada).
- Fornecer arquitetura limpa, organizada e modular.
- Atender todos os requisitos da disciplina.

---

## 4. Requisitos

### 4.1 Requisitos Funcionais

- Cadastrar usuários.
- Registrar métricas diárias:
  - Passos  
  - BPM médio  
  - Horas de sono  
  - Calorias
- Evitar registros duplicados no mesmo dia.
- Definir e atualizar metas:
  - Meta de passos  
  - Meta de média de sono  
- Atualização polimórfica das metas.
- Gerar alertas automáticos pela árvore de decisão.
- Armazenar alertas em uma **Fila**.
- Consultar histórico e médias com **Lista Encadeada**.
- Listar estatísticas por período.

### 4.2 Requisitos Não Funcionais

- Uso de JPA.
- Java 8+.
- Organização por camadas e pacotes.
- Banco MySQL 8.
- README completo.
- Commits frequentes e organizados.

---

## 5. Domínio do Sistema

### Entidades JPA

- **Usuario**  
  `id, nome, email, cpf, senha, bpmMaxAlerta, ...`

- **RegistroSaude**  
  `id, data, passos, bpmMedio, horasSono, kcal, usuario`

- **MetaSaude (abstrata)**
  - **MetaPassos**
  - **MetaMediaSono**  
  — com `@Inheritance(SINGLE_TABLE)`

- **Alerta**  
  `id, mensagem, severidade, dataHora, usuario, registroRelacionado`

---

## 6. Padrões de Projeto Aplicados (Essencial para Avaliação)

### 1. Herança e Polimorfismo — *MetaSaude*

Implementação:

MetaSaude (abstract)
├── MetaPassos
└── MetaMediaSono

Cada meta sobrescreve:
verificarProgresso(List<RegistroSaude>)

---

### 2. Padrão Composite — Árvore de Decisão POO

Estrutura:

| Classe            | Papel                                 |
|------------------|-----------------------------------------|
| `INoDecisao`      | Interface raiz com `avaliar()`          |
| `NoDeDecisao`     | Nó interno com condição + filhos        |
| `NoResultado`     | Folha que cria um alerta                |
| `NoResultadoVazio`| Folha neutra (sem alerta)               |

Exemplo de árvore:

Se BPM > limite → alerta crítico
Senão se sono < 5h → alerta de sono
Senão → nenhum alerta

---

## 7. Estruturas de Dados Próprias

A atividade exige EDs implementadas manualmente.

### ✔ Fila<T> (genérica)
Usada para armazenar alertas novos.

### ✔ ListaEncadeada
Usada para armazenar e processar registros de saúde (ex.: média de passos).

---

## 8. Diagrama de Classes

> ⚠ Inclua aqui a imagem do seu modelo UML.

O diagrama deve exibir:

- Entidades JPA  
- Herança de MetaSaude  
- Composite da árvore  
- Relação entre serviços/DAOs  
- ListaEncadeada + Fila  

---

## 9. Como Executar

### 1. Criar Banco

```sql
CREATE DATABASE projeto_saude;
```
Ajuste seu persistence.xml com usuário e senha.

2. Rodar Maven
```
mvn clean install
```

4. Executar a aplicação
Classe principal:
```
app.Main
```

## 10. Testes com Carga Alta
O projeto foi testado com 100.000+ registros usando java-faker.

Isso validou:
- desempenho da JPA
- consultas paginadas
- funcionamento da árvore de decisão
- consumo das EDs personalizadas

## 11. Considerações Finais
Este projeto demonstra:

- Padrões avançados de POO
- Estruturas de dados próprias
- Árvore de decisão orientada a objetos
- Persistência real com JPA/Hibernate
- Arquitetura limpa, modular e extensível

<img width="5821" height="1426" alt="Diagrama de Classes com Herança e Árvore de Decisão POO" src="https://github.com/user-attachments/assets/75fefa3b-450b-4e56-bc06-6a40353c7767" />
