import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class NoHuffman implements Comparable<NoHuffman> {
    char dado;
    int frequencia;
    NoHuffman esquerda, direita;

    public int compareTo(NoHuffman no) {
        return frequencia - no.frequencia;
    }
}

public class CodificacaoHuffman {

    private static Map<Character, String> codigosHuffman;

    public static void main(String[] args) {
        String mensagem = "Olá, Mundo!";
        codigosHuffman = codificar(mensagem);

        System.out.println("Mensagem Original: " + mensagem);
        System.out.println("Mensagem Codificada: " + obterMensagemCodificada(mensagem));
    }

    public static Map<Character, String> codificar(String mensagem) {
        Map<Character, Integer> frequencias = new HashMap<>();

        // Calcular a frequência de cada caractere na mensagem
        Map<Character, Integer> frequencias = new HashMap<>();

        // Calculando a frequência de cada caractere
        for (char c : mensagem.toCharArray()) {
            frequencias.put(c, frequencias.getOrDefault(c, 0) + 1);
        }

        // Criar uma fila de prioridade e adicionar cada caractere com sua frequência


        // Construir a árvore de Huffman
        private static NoHuffman construirArvoreHuffman() {
            while (filaPrioridade.size() > 1) {
                NoHuffman esquerda = filaPrioridade.poll();
                NoHuffman direita = filaPrioridade.poll();
                NoHuffman pai = new NoHuffman('\0', esquerda.frequencia + direita.frequencia);
                pai.esquerda = esquerda;
                pai.direita = direita;
                filaPrioridade.add(pai);
            }
            return filaPrioridade.peek();
        }

        // Gerar os códigos de Huffman para cada caractere

        // Retornar os códigos de Huffman mapeados para cada caractere
        return null;
    }

    private static String obterMensagemCodificada(String mensagem) {
        StringBuilder mensagemCodificada = new StringBuilder();
        for (char c : mensagem.toCharArray()) {
            mensagemCodificada.append(codigosHuffman.get(c));
        }
        return mensagemCodificada.toString();
    }
}