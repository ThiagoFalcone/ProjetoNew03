package kanban;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nomeEmpresa;
    private List<Projeto> projetos;  
    
    // Construtor
    public Empresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        this.projetos = new ArrayList<>();
    }

    // Getter e Setter para nomeEmpresa
    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
    
    public void adicionarProjeto(Projeto projeto) {
        projetos.add(projeto);
    }
    public List<Projeto> getProjetos() {
        return projetos;
    }
}
