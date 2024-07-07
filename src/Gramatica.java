import java.util.*;


public class Gramatica {

    //Estou considerando que o primeiro simbolo é o inicial

    private Map<String, List<String>> gramatica;

    public Gramatica() {
        gramatica = new LinkedHashMap<>();
    }

    public void gramatica(String texto){

        String[] producoes = texto.split("\n");
        for (String producao : producoes) {
            String[] partes = producao.split("->");
            String esquerda = partes[0].trim();
            String[] direitas = partes[1].trim().split("\\|");

            List<String> producoesDireitas = new ArrayList<>();
            for (String direita : direitas) {
                producoesDireitas.add(direita.trim());
            }
            gramatica.put(esquerda, producoesDireitas);
        }
    }

    public Map<String, List<String>> getGramatica() {
        return gramatica;
    }


}

