
class No {
    String conteudo;
    No sim;
    No nao;
    public No(String conteudo) {
        this.conteudo = conteudo;
        this.sim = null;
        this.nao = null;
    }
    public boolean ehConclusao() {
        return sim == null && nao == null;
    }
}