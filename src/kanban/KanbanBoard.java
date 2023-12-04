package kanban;

import java.util.ArrayList;
import java.util.List;

public class KanbanBoard {
    List<Projeto> projetos;

    public KanbanBoard() {
        projetos = new ArrayList<>();
    }

    public void adicionarProjeto(Projeto projeto) {
        projetos.add(projeto);
    }

    public void mostrarProjetos() {
        System.out.println("Projetos no Kanban Board:");
        for (Projeto projeto : projetos) {
            System.out.println("Nome do Projeto: " + projeto.nome);
            projeto.mostrarTarefas();
            System.out.println("------------------------------");
        }
    }

    public Projeto encontrarProjeto(String nomeProjeto) {
        for (Projeto projeto : projetos) {
            if (projeto.nome.equalsIgnoreCase(nomeProjeto)) {
                return projeto;
            }
        }
        return null; // Retorna null se o projeto não for encontrado
    }
}
