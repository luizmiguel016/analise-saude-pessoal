# Sistema de Análise de Dados de Saúde Pessoal

Java • POO • JPA • Estruturas de Dados (Lista Encadeada, Fila, Árvore de Decisão)

## 1) Visão Geral
O **Sistema de Análise de Dados de Saúde Pessoal** possibilita que usuários registrem métricas diárias (passos, batimentos cardíacos, sono e alimentação), visualizem **gráficos de tendência**, definam e acompanhem **metas**, e recebam **alertas** quando valores saem de faixas normais. Os alertas são **exibidos no próprio sistema** (não enviados a dispositivos externos).

- **Stack:** Java, JPA (Hibernate), MySQL 8, Maven (admin via phpMyAdmin)
- **Paradigmas/Conceitos:** POO (encapsulamento, herança, polimorfismo), coleções + **Lista Encadeada** (registros por dia), **Fila** (alertas a processar), **Árvore de Decisão** (regras/heurísticas para alertas/insights).
- **Arquitetura:** camadas `dominio` (entidades), `repositorio` (DAO/JPA), `servico` (regras), `view` (UI, opcional), `estruturadados` (ED), `ml/decisao` (árvore de decisão).

## 2) Tabela de Conteúdos
- Visão Geral
- Objetivos do Projeto
- Requisitos
  - Funcionais
  - Não Funcionais
- Domínio e Modelo de Dados
- Estruturas de Dados e POO
- Organização do Projeto
- Configuração e Execução
- Exemplos de Uso
- Testes
- Relatórios e Gráficos
- Roadmap
- Limitações Conhecidas
- Como este projeto atende aos critérios de avaliação
- Contribuição
- Licença

## 3) Objetivos do Projeto
- Aplicar **POO** de forma clara.
- Persistir dados com **JPA**, modelando entidades e relacionamentos.
- Utilizar **estruturas de dados próprias** (lista encadeada, fila) e **árvore de decisão** para regras/insights.
- Entregar um sistema executável com **README** completo e repositório no GitHub.
- Aplicar corretamente conceitos de POO (encapsulamento, coesão e uso criterioso de interfaces/abstrações).

## 4) Requisitos
### 4.1) Funcionais
- Cadastrar **Usuário** e **Registros de Saúde** (passos, BPM, sono, alimentação/kcal) por dia.
- Definir **Metas de Saúde** (ex.: passos/dia, horas de sono) e acompanhar progresso.
- Gerar **gráficos de tendência** (ex.: BPM médio diário, passos/semana, sono/semana).
- Detectar **alertas** (ex.: BPM fora do intervalo normal do usuário; metas não cumpridas) e colocá-los em **Fila** para processamento/apresentação.

### 4.2) Não Funcionais
- Organização em camadas e pacotes claros.
- **Mensagens de commit** frequentes e significativas.
- **Testes** unitários de serviços, estruturas de dados e regras básicas.

## 5) Domínio e Modelo de Dados
**Entidades JPA** (pacote `domain`):
- `Usuário` (id, nome, email, perfil/limiares de BPM, etc.)
- `RegistroSaude` (id, data, passos, bpmMedio, horasSono, kcal, usuario)
- `MetaSaude` (id, tipoMeta, valorAlvo, periodo, status, usuario)
- `Alerta` (id, tipo, mensagem, severidade, dataHora, usuario, registroRelacionado)
