import java.util.ArrayList;
import java.util.Random;

class No {
    int valor;
    No esquerdo, direito;

    No(int item) {
        valor = item;
        esquerdo = direito = null;
    }
}

class ArvoreBinaria {
    No raiz;

    void rotacaoDireita(No avo, No pai, No filho) {
        if (avo != null) {
            if (avo.esquerdo == pai) {
                avo.esquerdo = filho;
            } else {
                avo.direito = filho;
            }
        } else {
            raiz = filho;
        }
        pai.esquerdo = filho.direito;
        filho.direito = pai;
    }

    void rotacaoEsquerda(No avo, No pai, No filho) {
        if (avo != null) {
            if (avo.esquerdo == pai) {
                avo.esquerdo = filho;
            } else {
                avo.direito = filho;
            }
        } else {
            raiz = filho;
        }
        pai.direito = filho.esquerdo;
        filho.esquerdo = pai;
    }

    void criarEspinha() {
        No avo = null;
        No pai = raiz;
        while (pai != null) {
            if (pai.esquerdo != null) {
                No filho = pai.esquerdo;
                rotacaoDireita(avo, pai, filho);
                pai = filho;
            } else {
                avo = pai;
                pai = pai.direito;
            }
        }
    }

    void criarArvoreBalanceada(int n) {
        int m = (int) Math.pow(2, Math.floor(Math.log(n + 1) / Math.log(2))) - 1;
        comprimir(n - m);
        for (m = m / 2; m > 0; m /= 2) {
            comprimir(m);
        }
    }

    void comprimir(int contagem) {
        No avo = null;
        No pai = raiz;
        while (contagem > 0) {
            No filho = pai.direito;
            if (filho != null) {
                rotacaoEsquerda(avo, pai, filho);
                avo = filho;
                pai = filho.direito;
            } else {
                break;
            }
            contagem--;
        }
    }

    void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    No inserirRec(No raiz, int valor) {
        if (raiz == null) {
            raiz = new No(valor);
            return raiz;
        }
        if (valor < raiz.valor)
            raiz.esquerdo = inserirRec(raiz.esquerdo, valor);
        else if (valor > raiz.valor)
            raiz.direito = inserirRec(raiz.direito, valor);
        return raiz;
    }

    int tamanho(No no) {
        if (no == null) {
            return 0;
        }
        return tamanho(no.esquerdo) + 1 + tamanho(no.direito);
    }

    void adicionarNumerosAleatorios(int quantidade, int min, int max) {
        Random rand = new Random();
        for (int i = 0; i < quantidade; i++) {
            int num = rand.nextInt((max - min) + 1) + min;
            inserir(num);
        }
    }

    void balancearArvore() {
        criarEspinha();
        int tamanho = tamanho(raiz);
        criarArvoreBalanceada(tamanho);
    }
}

public class Principal {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        arvore.adicionarNumerosAleatorios(100, 0, 100);
        arvore.balancearArvore();

        arvore.adicionarNumerosAleatorios(20, 0, 100);
        arvore.balancearArvore();
    }
}
