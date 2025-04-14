// Classe que representa uma fila de objetos do tipo Pixel
class Fila {
    private Node inicio = null;
    private Node fim = null;

    public Fila() {
    }

    // Método para adicionar um novo elemento ao final da fila
    public void enfileirar(Pixel valor) {
        Node novoNo = new Node(valor); 
        if (this.fim == null) {
            this.inicio = novoNo;
        } else {
            this.fim.proximo = novoNo;
        }

        this.fim = novoNo;
    }

    // Método para remover e retornar o primeiro elemento da fila
    public Pixel desenfileirar() {
        if (this.inicio == null) {
            return null;
        } else {
            Pixel valorRemovido = this.inicio.valor;

            this.inicio = this.inicio.proximo;

            if (this.inicio == null) {
                this.fim = null;
            }

            // Retorna o valor removido
            return valorRemovido;
        }
    }

    // Método para verificar se a fila está vazia
    public boolean estaVazia() {
        return this.inicio == null;
    }
}
