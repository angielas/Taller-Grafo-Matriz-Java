import java.util.ArrayDeque;
import java.util.Queue;

public class GrafoNoDirigido {
    private final boolean[][] matrizAdyacencia;

    public GrafoNoDirigido(int cantidadVertices) {
        if (cantidadVertices <= 0) {
            throw new IllegalArgumentException("La cantidad de vertices debe ser positiva.");
        }
        matrizAdyacencia = new boolean[cantidadVertices][cantidadVertices];
    }

    public void agregarArista(int origen, int destino) {
        validarVertice(origen);
        validarVertice(destino);

        matrizAdyacencia[origen][destino] = true;
        matrizAdyacencia[destino][origen] = true;
    }

    public void mostrarMatriz() {
        System.out.println("Matriz de adyacencia:");
        for (boolean[] fila : matrizAdyacencia) {
            for (boolean adyacente : fila) {
                System.out.print((adyacente ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int inicio) {
        validarVertice(inicio);
        boolean[] visitados = new boolean[matrizAdyacencia.length];
        System.out.print("DFS desde " + inicio + ": ");
        dfsRecursivo(inicio, visitados);
        System.out.println();
    }

    private void dfsRecursivo(int vertice, boolean[] visitados) {
        visitados[vertice] = true;
        System.out.print(vertice + " ");

        for (int vecino = 0; vecino < matrizAdyacencia.length; vecino++) {
            if (matrizAdyacencia[vertice][vecino] && !visitados[vecino]) {
                dfsRecursivo(vecino, visitados);
            }
        }
    }

    public void bfs(int inicio) {
        validarVertice(inicio);
        boolean[] visitados = new boolean[matrizAdyacencia.length];
        Queue<Integer> pendientes = new ArrayDeque<>();

        visitados[inicio] = true;
        pendientes.add(inicio);
        System.out.print("BFS desde " + inicio + ": ");

        while (!pendientes.isEmpty()) {
            int vertice = pendientes.remove();
            System.out.print(vertice + " ");

            for (int vecino = 0; vecino < matrizAdyacencia.length; vecino++) {
                if (matrizAdyacencia[vertice][vecino] && !visitados[vecino]) {
                    visitados[vecino] = true;
                    pendientes.add(vecino);
                }
            }
        }
        System.out.println();
    }

    private void validarVertice(int vertice) {
        if (vertice < 0 || vertice >= matrizAdyacencia.length) {
            throw new IndexOutOfBoundsException(
                    "Vertice fuera de rango: " + vertice + ". Rango valido: 0-"
                            + (matrizAdyacencia.length - 1) + ".");
        }
    }

    public static void main(String[] args) {
        GrafoNoDirigido grafo = new GrafoNoDirigido(6);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(2, 5);

        grafo.mostrarMatriz();
        grafo.dfs(0);
        grafo.bfs(0);
    }
}
