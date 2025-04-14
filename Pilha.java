class Pilha {
    // Referência para o topo da pilha
    private Node topo;

    public Pilha() {
        this.topo = null;
    }
    // Método para inserir um novo elemento (tipo Pixel) na pilha
    public void insere(Pixel valor) {
        Node novoNo = new Node(valor);

        // Faz o novo nó apontar para o antigo topo
        novoNo.proximo = topo;

        // Atualiza o topo para o novo nó
        topo = novoNo;
    }

    public Pixel remover() {
        // Se a pilha estiver vazia, retorna null
        if (topo == null) {
            return null;
        }

        Pixel valorRemovido = topo.valor;

        // Move o topo para o próximo nó
        topo = topo.proximo;

        return valorRemovido;
    }

    public boolean estaVazia() {
        return topo == null;
    }
}
