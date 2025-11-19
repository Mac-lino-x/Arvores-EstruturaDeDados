import java.util.Scanner;
public class AnimalClassifier {

    
    private static final String SEPARADOR = "---------------------------------------------";
    private static final String SIMBOLO_PERGUNTA = "🧐";
    private static final String SIMBOLO_RESPOSTA = "✅";

    private No raiz;
    private final Scanner scanner; 

    public AnimalClassifier() {
        this.scanner = new Scanner(System.in);
        construirArvoreInicial();
    }

    private void construirArvoreInicial() {
        raiz = new No("Vive primariamente na água?");
        No possui_pele_lisa = new No("Tem pele lisa e úmida, sem escamas ou pelos?");
        raiz.sim = possui_pele_lisa;

        No tem_pelo = new No("É coberto por pelos/cabelo?");
        raiz.nao = tem_pelo;

        possui_pele_lisa.sim = new No("sapo");

        possui_pele_lisa.nao = new No("salamandra");

        tem_pelo.sim = new No("cachorro");

        No e_domestico = new No("É um animal tipicamente domesticado/de estimação?");
        tem_pelo.sim = e_domestico;
        e_domestico.sim = new No("cachorro");
        e_domestico.nao = new No("rato"); 
        System.out.println("\n" + SEPARADOR);
        System.out.println("🌳 A árvore inicial tem: Sapo, Salamandra, Cachorro, Rato e Tartaruga (extra).");
        System.out.println(SEPARADOR);
    }

    public void iniciarJogo() {
        System.out.println("\n🎉 BEM-VINDO ao Adivinha-Animal-Simples! 🎉");
        System.out.println(SEPARADOR);
        System.out.println("Pense em um dos animais da lista inicial (Sapo, Salamandra, Cachorro, Rato).");
        System.out.println("Vou tentar adivinhar com uma sequência de perguntas 'Sim' ou 'Não' (S/N).");
        System.out.println(SEPARADOR);

        navegarArvore(raiz);

        System.out.println(SEPARADOR);
        System.out.println("Obrigado por jogar! Até a próxima! 👋");
        scanner.close();
    }

    private void navegarArvore(No atual) {

        if (atual.ehConclusao()) {
            System.out.println(SIMBOLO_RESPOSTA + " CONCLUÍDO! O seu animal é um(a) **" + atual.conteudo.toUpperCase() + "**!");
            return;
        }
        System.out.print("\n" + SIMBOLO_PERGUNTA + " PERGUNTA: **" + atual.conteudo + "** (S/N): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            if (atual.sim != null) {
                navegarArvore(atual.sim);
            } else {
                System.out.println("🤔 Ops! Faltou um nó 'SIM' aqui. A árvore está incompleta!");
            }
        } else if (resposta.equals("n")) {
            if (atual.nao != null) {
                navegarArvore(atual.nao);
            } else {
                System.out.println("🤔 Ops! Faltou um nó 'NÃO' aqui. A árvore está incompleta!");
            }
        } else {
            System.out.println("❌ Resposta inválida! Por favor, use 'S' para Sim ou 'N' para Não.");
            navegarArvore(atual);
        }
    }
    public static void main(String[] args) {
        AnimalClassifier jogo = new AnimalClassifier();
        jogo.iniciarJogo();
    }
}
