# Sistema de Análise de Dados de Saúde Pessoal

Java • POO • JPA • Estruturas de Dados (Lista Encadeada, Fila, Árvore de Decisão)

## 1) Visão Geral

O **Sistema de Análise de Dados de Saúde Pessoal** possibilita que usuários registrem métricas diárias (passos, batimentos cardíacos, sono e alimentação), visualizem **gráficos de tendência**, definam e acompanhem **metas**, e recebam **alertas** quando valores saem de faixas normais. Há suporte para **importação de dados simulados** de dispositivos vestíveis (CSV).

- **Stack:** Java, JPA, phpMyAdmin, Maven
- **Paradigmas/Conceitos:** POO (encapsulamento, herança, polimorfismo), coleções + **Lista Encadeada** (registros por dia), **Fila** (alergas a processar), **Árvore de Decisão** (regras/heurísticas para alertas/insights).
* **Arquitetura:** camadas `domain` (entidades), `repository` (DAO/JPA), `service` (regras), `view` (UI), `datastructures` IED), `ml` (árvore de decisão).

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
- Importação de Dados (CSV)
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
- Utilizar **Estruturas de dados** própirias (lista encadeada, fila) e **árvore de decisão** para regras/insights.
- Entregar um sistema executável com **README** completo e repositório no GitHub.

## 4) Requisitos
### 4.1) Funcionais
- Cadastrar **Usuário** e **Registros de Saúde** (passos, BPM, sono, alimentação/kcal) por dia.
- Definir **Metas de Saúde** (ex.: passos/dia, horas de sono). Acompanhar progresso.
- Gerar **gráficos de tendência** (ex.: BPM médio diário, passos/semana, sono/semana).
- Detectar **alertas** (ex.: BPM fora do intervalo normal do usuário; metas não cumpridas) e colocá-los em **Fila** para processamento/apresentação.
- **Importar CSV** com dados simulados de vestíveis.

### Não Funcionais
- Organização em **camadas** e **pacotes** claros.
- **Mensagens de commit** frequentes e significativas.
- **Testes** unitários de serviços, estruturas de dados e regras básicas.

## 5) Domínio e Modelo de Dados
**Entidades JPA** (pacote `domain`):
- `Usuário` (id, nome, email, perfil/limiares de BPM, etc.)
- `RegistroSaude` (id, data, passos, bpmMedio, horasSono, kcal, usuario)
- `MetaSaude` (id, tipoMeta, valorAlvo, periodo, status, usuario)
- `Alerta` (id, tipo, mensagem, severidade, dataHora, usuario, registroRelacionado)
