import java.util.ArrayList;
import java.util.List;

public class Simplificacao {

    private List<Character> simboloesInuteis;
    private StringBuilder esquerdaConcatenada;
    private String esquerdaStr;
    private List<String> producoesVazias;

    Simplificacao(){
        simboloesInuteis = new ArrayList<Character>();
        esquerdaConcatenada = new StringBuilder();
        esquerdaStr = "";
        producoesVazias = new ArrayList<String>();
    }

    public List<Character> simbolosInuteis(List<String> esquerda, List<String> direita){
        for (String s : esquerda) {
            esquerdaConcatenada.append(s);
        }
        esquerdaStr = esquerdaConcatenada.toString();

        for(String dir : direita){
            for(Character caracter : dir.toCharArray()) {
                if (Character.isUpperCase(caracter) && !esquerdaStr.contains(String.valueOf(caracter))) {
                    simboloesInuteis.add(caracter);
                }
            }
        }
        return simboloesInuteis;
    }

    public List<String> simbolosInalcancaveis(List<String> esquerda, List<String> direita){
        for (int i = 1; i < esquerda.size(); i++){
            boolean encontrou = false;
            for(int j = 0; j < direita.size(); j++){
                if(i != j && !direita.get(j).contains(esquerda.get(i))){
                    encontrou = true;
                }

                if(i != j && direita.get(j).contains(esquerda.get(i))){
                    encontrou = false;
                    break;
                }
            }
            if(encontrou == true){
                producoesVazias.add(esquerda.get(i));
            }
        }
        return producoesVazias;
    }



}
