package kanban;

import java.util.ArrayList;
import java.util.List;

enum TipoUsuario {
    ADMINISTRADOR,
    LIDER,
    COLABORADOR
}

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private String cpf;
    private TipoUsuario tipoUsuario;

    public enum Permissao {
        VISUALIZAR,
        EDITAR
    }

    public Usuario(String nome, String login, String senha, String cpf, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public boolean podeAcessarOpcoesAvancadas() {
        return tipoUsuario == TipoUsuario.ADMINISTRADOR || tipoUsuario == TipoUsuario.LIDER;
    }
    
    public boolean podeCriarNovaEmpresa() {
        return this.getTipoUsuario() == TipoUsuario.ADMINISTRADOR;
    }

    public boolean podeAcessarProjetos() {
        return tipoUsuario == TipoUsuario.ADMINISTRADOR || tipoUsuario == TipoUsuario.LIDER;
    }

    public boolean podeAcessarTarefasAcoes() {
        return tipoUsuario == TipoUsuario.ADMINISTRADOR || tipoUsuario == TipoUsuario.COLABORADOR;
    }

    public boolean podeAcessarTodasAsAcoesETarefas() {
        return tipoUsuario == TipoUsuario.ADMINISTRADOR || tipoUsuario == TipoUsuario.LIDER;
    }

    public boolean podeEditarProjetos() {
        return tipoUsuario == TipoUsuario.ADMINISTRADOR;
    }

    public boolean temPermissao(Permissao permissao) {
        switch (this.tipoUsuario) {
            case ADMINISTRADOR:
                return true; // Administrador tem acesso a todas as permissões
            case LIDER:
                return permissao == Permissao.VISUALIZAR; // Líder só pode visualizar
            case COLABORADOR:
                return permissao == Permissao.EDITAR; // Colaborador só pode editar
            default:
                return false;
        }
    }
}
