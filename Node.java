public class Node {
    Pixel valor;
    Node proximo;

    // Construtor que recebe um Pixel e cria um novo nó com esse valor
    public Node(Pixel valor) {
        this.valor = valor;
        this.proximo = null; 
    }
}
