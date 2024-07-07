import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class EscreverArquivo {

    private static final String NOMEARQUIVO = "saida.txt";


    public static void escreveSimplificacao(Map<String, List<String>> producoes, String texto){
        try {
            FileWriter fileWriter = new FileWriter(NOMEARQUIVO);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(texto + "\n");
            for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
                String esquerda = prod.getKey();
                List<String> direita = prod.getValue();

                printWriter.println(esquerda + " -> " + String.join(" | ", direita));
            }

            printWriter.println("\n");
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public static void escreveChomsky(Map<String, List<String>> producoes, String texto){
        try {
            FileWriter fileWriter = new FileWriter(NOMEARQUIVO, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(texto + "\n");
            for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
                String esquerda = prod.getKey();
                List<String> direita = prod.getValue();

                printWriter.println(esquerda + " -> " + String.join(" | ", direita));
            }

            printWriter.println("\n");
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
        }
    }


}
