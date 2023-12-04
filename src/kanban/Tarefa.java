package kanban;

import java.util.ArrayList;
import java.util.List;

public class Tarefa {
    private String nome;
    private List<Acao> acoes;
    private int percentConclusao;
    private StatusTarefa status;
    private Usuario usuarioResponsavel;

    public enum StatusTarefa {
        EM_ANDAMENTO,
        CONCLUIDA
    }

    public Tarefa(String nome, Usuario usuarioResponsavel) {
        this.nome = nome;
        this.acoes = new ArrayList<>();
        this.percentConclusao = 0;
        this.status = StatusTarefa.EM_ANDAMENTO;
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public void adicionarAcao(Acao acao) {
        acoes.add(acao);
        calcularPercentConclusao();
        calcularStatusTarefa();
    }

    public void calcularPercentConclusao() {
        int totalAcoes = acoes.size();
        int somaPercentagens = 0;

        for (Acao acao : acoes) {
            somaPercentagens += acao.getPercentConclusao();
        }

        percentConclusao = totalAcoes > 0 ? somaPercentagens / totalAcoes : 0;
    }

    public void mostrarAcoes() {
        System.out.println("Ações da Tarefa: " + nome);
        for (Acao acao : acoes) {
            System.out.println(acao.getNome() + " - Concluída: " + acao.getStatus());
        }
    }

    public Acao encontrarAcao(String nomeAcao) {
        for (Acao acao : acoes) {
            if (acao.getNome().equals(nomeAcao)) {
                return acao;
            }
        }
        return null;
    }

    public void mostrarDetalhes() {
        System.out.println("Detalhes da Tarefa: " + nome);
        System.out.println("% de Conclusão: " + getPercentConclusao() + "%");
        System.out.println("Status: " + status);
    }

    public String getNome() {
        return nome;
    }

    public int getPercentConclusao() {
    calcularPercentConclusao();
    return percentConclusao;
}

    public List<Acao> getAcoes() {
        return acoes;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void calcularStatusTarefa() {
        boolean todasConcluidas = true;

        for (Acao acao : acoes) {
            if (acao.getStatus() != Acao.StatusAcao.CONCLUIDA) {
                todasConcluidas = false;
                break;
            }
        }

        status = todasConcluidas ? StatusTarefa.CONCLUIDA : StatusTarefa.EM_ANDAMENTO;
    }

    // Métodos para verificar permissões

    public boolean podeSerVisualizadaPorUsuario(Usuario usuario) {
        return usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR || usuario.equals(usuarioResponsavel);
    }

    public boolean podeSerEditadaPorUsuario(Usuario usuario) {
        return (usuario.getTipoUsuario() == TipoUsuario.COLABORADOR && usuario.equals(usuarioResponsavel)) || usuario.temPermissao(Usuario.Permissao.EDITAR);
    }
}
