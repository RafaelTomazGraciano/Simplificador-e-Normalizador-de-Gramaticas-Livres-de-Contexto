import java.util.List;

public class Main {
    public static void main(String[] args) {

        //le arquivo de texto
        String texto = LeituraArquivo.lerArquivo("gramatica.txt");

        //divide a gramatica a esquerda e a direita
        Gramatica gramatica = new Gramatica();
        gramatica.gramatica(texto);
        List<String> esquerda =  gramatica.getEsquerda();
        List<String> direita =  gramatica.getDireita();
        System.out.println(esquerda);
        System.out.println(direita);

        // SIMPLIFICACAO
        Simplificacao simplificacao = new Simplificacao();

        // símbolos inalcançáveis
        List<Character> simbolosInuteis = simplificacao.simbolosInuteis(esquerda, direita);
        System.out.println("símbolos inúteis: " + simbolosInuteis);

        // símbolos inalcançáveis
        List<String> simbolosInalcancaveis = simplificacao.simbolosInalcancaveis(esquerda, direita);
        System.out.println("símbolos inalcançáveis: " + simbolosInalcancaveis);

        // susbtituições de produções
    }
}