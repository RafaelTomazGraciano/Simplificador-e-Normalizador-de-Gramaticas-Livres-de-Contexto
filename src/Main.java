import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //le arquivo de texto
        String texto = LeituraArquivo.lerArquivo("gramatica.txt");

        //gera a gramatica
        Gramatica gramatica = new Gramatica();
        gramatica.gramatica(texto);
        Map<String, List<String>> producoes = gramatica.getGramatica();
        System.out.println(producoes);


        // SIMPLIFICACAO
        Simplificacao simplificacao = new Simplificacao();

        // produções vazias
        simplificacao.producoesVazias(producoes);
        System.out.println(producoes);

        // susbtituições de produções
        simplificacao.substituicoesProducoes(producoes);
        System.out.println(producoes);

        // símbolos inuteis
        simplificacao.simbolosInuteis(producoes);
        System.out.println(producoes);

        // símbolos inalcançáveis
        simplificacao.simbolosInalcancaveis(producoes);
        System.out.println(producoes);

        // Escreve a simplificacao no arquivo
        EscreverArquivo.escreveSimplificacao(producoes, "SIMPLIFICAÇÃO");

        // Normas


        //Chomsky

        // Escreve Chomsky no arquivo
        //EscreverArquivo.escreveChomsky(producoes, "CHOMSKY");

    }
}