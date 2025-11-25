# Sistema de Análise de Dados de Saúde Pessoal

Java • POO • JPA • Estruturas de Dados (Lista Encadeada, Fila, Árvore de Decisão)

## 1) Visão Geral
O **Sistema de Análise de Dados de Saúde Pessoal** possibilita que usuários registrem métricas diárias (passos, batimentos cardíacos, sono e alimentação), visualizem **gráficos de tendência**, definam e acompanhem **metas**, e recebam **alertas** quando valores saem de faixas normais. Os alertas são **exibidos no próprio sistema** (não enviados a dispositivos externos).

- **Stack:** Java, JPA (Hibernate), MySQL 8, Maven (admin via phpMyAdmin)
- **Paradigmas/Conceitos:** POO (Encapsulamento, Herança, Polimorfismo), Coleções + **Lista Encadeada** (registros por dia), **Fila** (alertas a processar), **Árvore de Decisão POO** (para classificação e alertas).
- **Arquitetura:** camadas `dominio` (entidades), `repositorio` (DAO/JPA), `servico` (regras), `view` (UI, opcional), `estruturadados` (ED), `dominio.arvore` (estrutura da árvore).

## 2) Tabela de Conteúdos
(Seu índice permanece o mesmo...)

## 3) Objetivos do Projeto
- Aplicar **POO** de forma clara.
- Persistir dados com **JPA**, modelando entidades e relacionamentos.
- Utilizar **estruturas de dados próprias** (lista encadeada, fila) e uma **Árvore de Decisão POO** para regras de negócio.
- Entregar um sistema executável com **README** completo e repositório no GitHub.
- Aplicar corretamente conceitos de POO (encapsulamento, coesão e uso criterioso de interfaces/abstrações).

## 4) Requisitos
### 4.1) Funcionais
- Cadastrar **Usuário** e **Registros de Saúde** (passos, BPM, sono, alimentação/kcal) por dia.
- Definir **Metas de Saúde** (ex.: passos/dia, horas de sono) e acompanhar progresso de forma polimórfica.
- Gerar **gráficos de tendência** (ex.: BPM médio diário, passos/semana, sono/semana).
- Utilizar uma **Árvore de Decisão** para classificar a situação do usuário e gerar **alertas**, colocando-os em **Fila** para processamento/apresentação.

### 4.2) Não Funcionais
(Permanece o mesmo...)

## 5) Domínio e Modelo de Dados
**Entidades JPA** (pacote `domain`):
- `Usuário` (id, nome, email, perfil/limiares de BPM, etc.)
- `RegistroSaude` (id, data, passos, bpmMedio, horasSono, kcal, usuario)
- `Alerta` (id, tipo, mensagem, severidade, dataHora, usuario, registroRelacionado)

### 5.1) Padrões de POO Aplicados

Para ir além de um simples CRUD e aplicar os conceitos da disciplina, o domínio foi modelado usando dois padrões principais de POO:

#### 1. Herança e Polimorfismo (para `MetaSaude`)
Em vez de uma classe `MetaSaude` com um campo `tipoMeta` e múltiplos `if/else` no serviço, foi usada Herança:

- **`MetaSaude` (Classe Abstrata):** Define o "esqueleto" de uma meta, com atributos comuns (id, periodo, status) e um método abstrato: `calcularStatus()`.
- **`MetaPassos` (Herda de `MetaSaude`):** Implementa `calcularStatus()` com a lógica específica para contar passos.
- **`MetaMediaSono` (Herda de `MetaSaude`):** Implementa `calcularStatus()` com a lógica específica para calcular médias de sono.

**Persistência (JPA):** Este padrão é persistido usando a estratégia `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`, conforme estudado nos materiais de Mapeamento de Herança.

#### 2. Padrão Composite e Polimorfismo (para `ArvoreDeDecisao`)
Em vez de um serviço com um bloco `if/else` aninhado para gerar alertas (uma "árvore de decisão" procedural), a própria **estrutura de dados** da árvore foi modelada com objetos, aplicando POO:

- **`INoDecisao` (Interface):** Define um "contrato" polimórfico com um único método: `Optional<Alerta> avaliar(Usuario, RegistroSaude)`.
- **`NoDeDecisao` (Implementa `INoDecisao`):** Representa um "ramo" (branch) da árvore. Este objeto *possui* outros dois objetos `INoDecisao` (um "sim" e um "não") e uma "pergunta" (Predicate). Ele delega a chamada para um de seus filhos baseado na resposta da pergunta.
- **`NoResultado` (Implementa `INoDecisao`):** Representa uma "folha" (leaf) da árvore. Contém o `Alerta` final a ser gerado se esse nó for alcançado.
- **`NoResultadoVazio` (Implementa `INoDecisao`):** Uma folha que não faz nada (retorna `Optional.empty()`), permitindo que a árvore termine sem gerar um alerta.

O `ServicoDeAlerta` apenas "monta" essa árvore de objetos uma vez e, para cada novo `RegistroSaude`, ele simplesmente chama `raizDaArvore.avaliar()`. O polimorfismo cuida de navegar pela árvore inteira de forma limpa e desacoplada.

## 6) Diagrama de Classes

O diagrama abaixo reflete a arquitetura de POO descrita acima, demonstrando o uso de Herança (para `MetaSaude`) e o padrão Composite (para `INoDecisao`).

<img width="5821" height="1426" alt="Diagrama de Classes com Herança e Árvore de Decisão POO" src="https://github.com/user-attachments/assets/75fefa3b-450b-4e56-bc06-6a40353c7767" />
