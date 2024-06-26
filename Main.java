import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Pilha<Character> pilha = new Pilha<>();

        Scanner sc = new Scanner(System.in);
        String entrada = sc.nextLine();
        StringBuilder saida = new StringBuilder();

        while (!entrada.equals("FIM")) {
            int count = 0;
            saida = new StringBuilder();
            while (count < entrada.length()) {
                if (entrada.charAt(count) != ' ') {
                    if (isAlphaNumeric(entrada.charAt(count))) {
                        saida.append(entrada.charAt(count));
                        count ++;
                    } else if (entrada.charAt(count) == '+' ||
                            entrada.charAt(count) == '-' ||
                            entrada.charAt(count) == '*' ||
                            entrada.charAt(count) == '/')
                    {
                        switch (entrada.charAt(count)) {
                            case '+', '-':
                                if(pilha.vazia()){
                                    pilha.empilhar(entrada.charAt(count));
                                    count ++;
                                } else if(pilha.topo.getItem().toString().equals("/") ||
                                        pilha.topo.getItem().toString().equals("*") ||
                                        pilha.topo.getItem().toString().equals("+") ||
                                        pilha.topo.getItem().toString().equals("-")) {
                                    saida.append(pilha.desempilhar());
                                    pilha.empilhar(entrada.charAt(count));
                                    count ++;
                                }else{
                                    pilha.empilhar(entrada.charAt(count));
                                    count ++;
                                }
                                break;
                            case '*', '/':
                                if(pilha.vazia()){
                                    pilha.empilhar(entrada.charAt(count));
                                    count ++;
                                } else if(pilha.topo.getItem().toString().equals("/") ||
                                        pilha.topo.getItem().toString().equals("*")
                                ){
                                    saida.append(pilha.desempilhar());
                                    pilha.empilhar(entrada.charAt(count));
                                    count ++;
                                }else{
                                    pilha.empilhar(entrada.charAt(count));
                                    count ++;
                                }
                                break;

                        }

                    } else if (entrada.charAt(count) == '(') {
                        pilha.empilhar(entrada.charAt(count));
                        count++;
                    } else if (entrada.charAt(count) == ')') {
                        while (!(pilha.topo.getItem().toString().equals("("))) {
                            saida.append(" ").append(pilha.desempilhar());
                        }
                        pilha.desempilhar();
                        count++;
                    }

                } else {
                    saida.append(" ");
                    count++;
                }
            }
            while(!pilha.vazia()){
                saida.append(" ").append(pilha.desempilhar());
            }
            String output = saida.toString().replaceAll("\\s+", " ");
            System.out.println(output + " ");
            entrada = sc.nextLine();
        }


    }

    public static boolean isAlphaNumeric(char str) {
        return Character.isLetterOrDigit(str);
    }

    public static class Value{
        public Value(String value) {
            this.value = value;
        }

        String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class Pilha<E> {

        private Celula<E> topo;
        private Celula<E> fundo;

        public Pilha() {

            Celula<E> sentinela = new Celula<E>();
            fundo = sentinela;
            topo = sentinela;

        }

        public boolean vazia() {
            return fundo == topo;
        }

        public void empilhar(E item) {

            topo = new Celula<E>(item, topo);
        }

        public E desempilhar() {

            E desempilhado = consultarTopo();
            topo = topo.getProximo();
            return desempilhado;

        }

        public E consultarTopo() {

            if (vazia()) {
                throw new NoSuchElementException("Nao há nenhum item na pilha!");
            }

            return topo.getItem();

        }
    }
    public static class Celula<T> {

        private final T item;
        private Celula<T> proximo;

        public Celula() {
            this.item = null;
            setProximo(null);
        }

        public Celula(T item) {
            this.item = item;
            setProximo(null);
        }

        public Celula(T item, Celula<T> proximo) {
            this.item = item;
            this.proximo = proximo;
        }

        public T getItem() {
            return item;
        }

        public Celula<T> getProximo() {
            return proximo;
        }

        public void setProximo(Celula<T> proximo) {
            this.proximo = proximo;
        }
    }
}

