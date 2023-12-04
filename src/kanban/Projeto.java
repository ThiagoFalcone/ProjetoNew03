package kanban;

import java.util.ArrayList;
import java.util.List;

public class Projeto {
    String nome;
    String descricao;
    Usuario responsavel; // Adicionando o responsável pelo projeto
    List<Tarefa> tarefas;
    Empresa empresa; // Adicionando a associação com a empresa

    public Projeto(String nome, String descricao, Usuario responsavel, Empresa empresa) {
        this.nome = nome;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.tarefas = new ArrayList<>();
        this.empresa = empresa; // Associando o projeto à empresa
        empresa.adicionarProjeto(this); // Adicionando o projeto à lista de projetos da empresa
    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void mostrarTarefas() {
        System.out.println("Tarefas do Projeto: " + nome + " - Descrição: " + descricao);
        for (Tarefa tarefa : tarefas) {
            System.out.println("Nome da Tarefa: " + tarefa.getNome() + " - % de Conclusão: " + tarefa.getPercentConclusao() + "%");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public Tarefa encontrarTarefa(String nomeTarefa) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getNome().equals(nomeTarefa)) {
                return tarefa;
            }
        }
        return null;
    }

    // Verifica se o usuário pode visualizar o projeto
    public boolean podeSerVisualizadoPorUsuario(Usuario usuario) {
        return usuario.podeAcessarTodasAsAcoesETarefas() || this.responsavel.equals(usuario);
    }

    public boolean podeSerEditadoPorUsuario(Usuario usuario) {
        return usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR || usuario.equals(responsavel) || usuario.temPermissao(Usuario.Permissao.EDITAR);
    }

    public void mostrarDetalhes() {
        System.out.println("Empresa Responsável: " + empresa.getNomeEmpresa());
        System.out.println("Detalhes do Projeto: " + nome + " - Descrição: " + descricao);
       
        int totalTarefas = tarefas.size();
        int totalAcoes = 0;
        int somaPercentagens = 0;

        for (Tarefa tarefa : tarefas) {
            totalAcoes += tarefa.getAcoes().size();
            for (Acao acao : tarefa.getAcoes()) {
                somaPercentagens += acao.getPercentConclusao();
            }
        }

        int percentagemTotal = totalAcoes > 0 ? somaPercentagens / totalAcoes : 0;

        System.out.println("% de Conclusão do Projeto: " + percentagemTotal + "%");

        for (Tarefa tarefa : tarefas) {
            System.out.println("  - Nome da Tarefa: " + tarefa.getNome() + " - % de Conclusão: " + tarefa.getPercentConclusao() + "%");
            for (Acao acao : tarefa.getAcoes()) {
                System.out.println("    * Nome da Ação: " + acao.getNome() +
                        "\n      - Descrição: " + acao.getDescricao() +
                        "\n      - Data de Início: " + acao.getDataInicio() +
                        "\n      - Data de Término: " + acao.getDataTermino() +
                        "\n      - Área Responsável: " + acao.getAreaResponsavel() +
                        "\n      - Usuário Responsável: " + acao.getUsuarioResponsavel() +
                        "\n      - % de Conclusão: " + acao.getPercentConclusao() +
                        "\n      - Status: " + getStatusAcaoString(acao.getStatus()) +
                        "\n");
            }
        }
    }

    private String getStatusAcaoString(Acao.StatusAcao status) {
        switch (status) {
            case NAO_INICIADA:
                return "Não Iniciada";
            case EM_ANDAMENTO:
                return "Em Andamento";
            case CONCLUIDA:
                return "Finalizado";
            default:
                return "Status Inválido";
        }
    }
}
