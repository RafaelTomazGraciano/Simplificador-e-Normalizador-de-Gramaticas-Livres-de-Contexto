import java.util.ArrayList;
import java.util.List;

public class Gramatica {

    private List<String> esquerda;
    private List<String> direita;

    Gramatica() {
        this.esquerda = new ArrayList<>();
        this.direita = new ArrayList<>();
    }

    public void gramatica(String texto) {
        String[] producoes = texto.split("\n");
        for(String producao : producoes){
            String[] partes = producao.split("\\s*->\\s*");
            this.esquerda.add(partes[0]);
            this.direita.add(partes[1]);
        }
    }


    public List<String> getEsquerda() {
        return esquerda;
    }


    public List<String> getDireita() {
        return direita;
    }
}

