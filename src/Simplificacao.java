import java.util.*;

public class Simplificacao {

    Map<String, List<String>> novasProducoes;
    private List<String> prodEsquerda;
    private List<String> prodDireita;
    private List<String> substituicoes;
    private Map<String, String> troca;
    private List<Character> simbolosInuteis;
    private StringBuilder esquerdaConcatenada;
    private String esquerdaStr;
    private List<String> simbolosInalcancaveis;


    Simplificacao(){

        novasProducoes = new LinkedHashMap<>();
        substituicoes = new ArrayList<>();
        prodEsquerda = new ArrayList<>();
        prodDireita = new ArrayList<>();
        troca = new HashMap<>();
        simbolosInuteis = new ArrayList<>();
        esquerdaConcatenada = new StringBuilder();
        esquerdaStr = "";
        simbolosInalcancaveis = new ArrayList<>();
    }

    //Estou considerando & como ε (epsilon) que representa vazio
    public void producoesVazias(Map<String, List<String>> producoes){

        //retira o vazio
        for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
            List<String> direita = prod.getValue();
            direita.remove("&");
        }

        // Remove chaves cujas listas de produções estão vazias
        // Remove usando o Iterator
        producoes.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }

    public void substituicoesProducoes(Map<String, List<String>> producoes){

        //acha onde deve ser feitas as substituicoes
        for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
            String esquerda = prod.getKey();
            List<String> direita = prod.getValue();
            prodEsquerda.add(esquerda);
            prodDireita.add(direita.toString());
            for (String dir : direita) {
                if (dir.length() == 1 && Character.isUpperCase(dir.charAt(0)) && !substituicoes.contains(dir)) {
                    substituicoes.add(dir);
                }
            }
        }

        //anota quais trocas devem ser realizadas
        for(int i = 0; i < prodEsquerda.size(); i++){
            if(substituicoes.contains(prodEsquerda.get(i))){
                troca.put( prodEsquerda.get(i), prodDireita.get(i));
            }
        }

        // Para armazenar as novas produções após a substituição
        for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
            String esquerda = prod.getKey();
            List<String> direita = prod.getValue();
            List<String> substitui = new ArrayList<>();

            for (String dir : direita) {
                if (troca.containsKey(dir)) {
                    //retira os colchetes
                    substitui.addAll(Arrays.asList(troca.get(dir).replaceAll("[\\[\\]]", "").split(", ")));
                } else {
                    substitui.add(dir);
                }
            }

            // Armazenar as produções substituídas no novo mapa
            novasProducoes.put(esquerda, substitui);
        }

        //passa as novasProducoes para producao
        producoes.clear();
        producoes.putAll(novasProducoes);

    }


    public void simbolosInuteis(Map<String, List<String>> producoes) {
        prodDireita.clear();
        for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
            String esquerda = prod.getKey();
            List<String> direita = prod.getValue();
            prodDireita.addAll(direita);
            esquerdaConcatenada.append(esquerda);
            esquerdaStr = esquerdaConcatenada.toString();

        }

        for (String dir : prodDireita) {
            for (Character caracter : dir.toCharArray()) {
                if (Character.isUpperCase(caracter) && !esquerdaStr.contains(String.valueOf(caracter))) {
                    simbolosInuteis.add(caracter);
                }
            }
        }

        // Para armazenar as novas produções após a substituição
        novasProducoes.clear();
        for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
            String esquerda = prod.getKey();
            List<String> direita = prod.getValue();
            List<String> substitui = new ArrayList<>();

            for (String dir : direita) {
                boolean contemSimboloInutil = false;
                for (char caracter : dir.toCharArray()) {
                    if (simbolosInuteis.contains(caracter)) {
                        contemSimboloInutil = true;
                        break;
                    }
                }
                if (!contemSimboloInutil) {
                    substitui.add(dir);
                }
            }

            // Armazenar as produções substituídas no novo mapa
            novasProducoes.put(esquerda, substitui);
        }

        //passa as novasProducoes para producao
        producoes.clear();
        producoes.putAll(novasProducoes);
    }


    public void simbolosInalcancaveis(Map<String, List<String>> producoes){
        prodDireita.clear();
        prodEsquerda.clear();
        for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
            String esquerda = prod.getKey();
            List<String> direita = prod.getValue();
            prodDireita.add(direita.toString());
            prodEsquerda.add(esquerda);
        }

        for (int i = 1; i < prodEsquerda.size(); i++){
            boolean encontrou = false;
            for(int j = 0; j < prodDireita.size(); j++){
                if(i != j && !prodDireita.get(j).contains(prodEsquerda.get(i))){
                    encontrou = true;
                }

                if(i != j && prodDireita.get(j).contains(prodEsquerda.get(i))){
                    encontrou = false;
                    break;
                }
            }
            if(encontrou){
                simbolosInalcancaveis.add(prodEsquerda.get(i));
            }
        }

        novasProducoes.clear();
        // Para armazenar as novas produções após a substituição
        for (Map.Entry<String, List<String>> prod : producoes.entrySet()) {
            String esquerda = prod.getKey();
            List<String> direita = prod.getValue();

            // Armazenar as produções substituídas no novo mapa
            if(!simbolosInalcancaveis.contains(esquerda)){
                novasProducoes.put(esquerda, direita);
            }

        }

        //passa as novasProducoes para producao
        producoes.clear();
        producoes.putAll(novasProducoes);

    }

}
