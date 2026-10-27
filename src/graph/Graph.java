
package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Queue;

/**
 * Represents a graph using an adjacency matrix.
 * The graph can be directed or undirected, weighted or unweighted.
 *
 * Supported operations include:
 * - Adding and removing edges
 * - Printing the adjacency matrix
 * - Breadth-first traversal (BFS)
 * - Depth-first traversal (DFS)
 * - Finding connected components
 * - Finding a path between two vertices
 * - Finding the shortest path using Dijkstra's algorithm
 * - Finding an Euler circuit in an undirected graph
 */
public class Graph {

    // Represents the absence of an edge.
    // A large finite value is used instead of Integer.MAX_VALUE
    // to reduce the risk of integer overflow during calculations.
    public static final int NO_EDGE = Integer.MAX_VALUE / 4;

    // Stores edge weights between vertices.
    // matrix[i][j] represents the edge from vertex i to vertex j.
    private final int[][] matrix;

    // The total number of vertices in the graph.
    private final int vertexCount;

    // True if edges have weights; otherwise, every edge has weight 1.
    private final boolean weighted;

    // True if edges have a direction; otherwise, edges are undirected.
    private final boolean directed;

    /**
     * Creates an unweighted, undirected graph.
     *
     * @param vertexCount the number of vertices
     */
    public Graph(int vertexCount) {
        this(vertexCount, false, false);
    }

    /**
     * Creates a graph with the specified properties.
     *
     * @param vertexCount the number of vertices
     * @param weighted true for a weighted graph
     * @param directed true for a directed graph
     */
    public Graph(int vertexCount, boolean weighted, boolean directed) {
        if (vertexCount <= 0) {
            throw new IllegalArgumentException(
                    "The number of vertices must be positive.");
        }

        this.vertexCount = vertexCount;
        this.weighted = weighted;
        this.directed = directed;
        this.matrix = new int[vertexCount][vertexCount];

        // Initially, no edges exist between different vertices.
        // The diagonal is zero because a vertex has distance zero to itself.
        for (int i = 0; i < vertexCount; i++) {
            Arrays.fill(matrix[i], NO_EDGE);
            matrix[i][i] = 0;
        }
    }

    /**
     * Creates an unweighted, undirected graph from an adjacency matrix.
     * Zero means no edge; nonzero means an edge.
     *
     * @param adjacencyMatrix the source adjacency matrix
     */
    public Graph(int[][] adjacencyMatrix) {
        this(adjacencyMatrix, false, false, 0);
    }

    /**
     * Creates a graph from a square adjacency matrix.
     *
     * @param adjacencyMatrix the source adjacency matrix
     * @param weighted true if the graph is weighted
     * @param directed true if the graph is directed
     * @param noEdgeValue the value representing a missing edge
     */
    public Graph(int[][] adjacencyMatrix, boolean weighted,
            boolean directed, int noEdgeValue) {

        // The matrix must exist and contain at least one vertex.
        if (adjacencyMatrix == null || adjacencyMatrix.length == 0) {
            throw new IllegalArgumentException(
                    "The matrix cannot be empty.");
        }

        int size = adjacencyMatrix.length;

        // An adjacency matrix must have the same number of rows and columns.
        for (int i = 0; i < size; i++) {
            if (adjacencyMatrix[i] == null
                    || adjacencyMatrix[i].length != size) {
                throw new IllegalArgumentException(
                        "The adjacency matrix must be square.");
            }
        }

        this.vertexCount = size;
        this.weighted = weighted;
        this.directed = directed;
        this.matrix = new int[size][size];

        // Convert each source value to the representation used by this graph.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                // This implementation does not support self-loops.
                if (i == j) {
                    matrix[i][j] = 0;
                } else if (adjacencyMatrix[i][j] == noEdgeValue) {

                    // Convert the source's missing-edge value to NO_EDGE.
                    matrix[i][j] = NO_EDGE;

                } else if (weighted) {

                    // Preserve the edge weight in a weighted graph.
                    matrix[i][j] = adjacencyMatrix[i][j];

                } else if (adjacencyMatrix[i][j] != 0) {

                    // Represent every existing unweighted edge with 1.
                    matrix[i][j] = 1;

                } else {

                    // Zero means no edge in an unweighted source matrix.
                    matrix[i][j] = NO_EDGE;
                }
            }
        }
    }

    /**
     * Returns the number of vertices.
     */
    public int getVertexCount() {
        return vertexCount;
    }

    /**
     * Returns whether this graph is weighted.
     */
    public boolean isWeighted() {
        return weighted;
    }

    /**
     * Returns whether this graph is directed.
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * Checks whether a vertex index is within the valid range.
     *
     * @param vertex the vertex index
     * @return true if the index is valid
     */
    private boolean isValidVertex(int vertex) {
        return vertex >= 0 && vertex < vertexCount;
    }

    /**
     * Stops an operation if the specified vertex does not exist.
     *
     * @param vertex the vertex index to check
     */
    private void checkVertex(int vertex) {
        if (!isValidVertex(vertex)) {
            throw new IllegalArgumentException(
                    "Invalid vertex index: " + vertex);
        }
    }

    /**
     * Checks whether an edge exists between two different vertices.
     *
     * @param from the starting vertex
     * @param to the destination vertex
     * @return true if an edge exists
     */
    private boolean hasEdge(int from, int to) {
        // Self-loops are not supported by this graph implementation.
        return from != to && matrix[from][to] != NO_EDGE;
    }

    /**
     * Converts a vertex index into a display label.
     * For example, 0 becomes A and 1 becomes B.
     */
    private char label(int vertex) {
        if (vertex < 26) {
            return (char) ('A' + vertex);
        }
        return '?';
    }

    /**
     * Prints the label of a vertex without starting a new line.
     *
     * @param vertex the vertex index
     */
    public void visit(int vertex) {
        System.out.print(label(vertex) + "  ");
    }

    /**
     * Adds an edge with the default weight of 1.
     *
     * @param from the starting vertex
     * @param to the destination vertex
     */
    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    /**
     * Adds an edge with a specified weight.
     *
     * In an unweighted graph, the stored value is always 1.
     * In an undirected graph, the edge is stored in both directions.
     * Self-loops are ignored.
     *
     * Negative weights are allowed, but Dijkstra's algorithm
     * cannot be used when a graph contains negative edge weights.
     *
     * @param from the starting vertex
     * @param to the destination vertex
     * @param weight the edge weight
     */
    public void addEdge(int from, int to, int weight) {
        checkVertex(from);
        checkVertex(to);

        // Ignore self-loops to keep the graph consistent with hasEdge().
        if (from == to) {
            return;
        }

        // Keep edge weights within the supported range.
        if (weight >= NO_EDGE || weight <= -NO_EDGE) {
            throw new IllegalArgumentException(
                    "The weight is outside the supported range.");
        }

        // Unweighted graphs use 1 regardless of the supplied weight.
        int value = weighted ? weight : 1;

        // Store the edge from the starting vertex to the destination.
        matrix[from][to] = value;

        // An undirected edge can be traversed in either direction.
        if (!directed) {
            matrix[to][from] = value;
        }
    }

    /**
     * Removes an edge between two vertices.
     *
     * In an undirected graph, the edge is removed in both directions.
     * Self-loops are ignored.
     *
     * @param from the starting vertex
     * @param to the destination vertex
     */
    public void removeEdge(int from, int to) {
        checkVertex(from);
        checkVertex(to);

        // Ignore self-loops because this graph does not support them.
        if (from == to) {
            return;
        }

        // Mark the directed edge as absent.
        matrix[from][to] = NO_EDGE;

        // Remove the reverse edge in an undirected graph.
        if (!directed) {
            matrix[to][from] = NO_EDGE;
        }
    }

    /**
     * Prints the adjacency matrix.
     *
     * A dash represents the absence of an edge between different vertices.
     * The diagonal displays zero.
     */
    public void printMatrix() {
        // Print the column labels.
        System.out.print("    ");

        for (int i = 0; i < vertexCount; i++) {
            System.out.printf("%-6c", label(i));
        }

        System.out.println();

        // Print each row label and its edge values.
        for (int i = 0; i < vertexCount; i++) {
            System.out.printf("%-4c", label(i));

            for (int j = 0; j < vertexCount; j++) {
                if (i != j && matrix[i][j] == NO_EDGE) {
                    System.out.printf("%-6s", "-");
                } else {
                    System.out.printf("%-6d", matrix[i][j]);
                }
            }

            System.out.println();
        }
    }

    /**
     * Performs breadth-first traversal (BFS).
     *
     * BFS visits neighboring vertices level by level using a queue.
     * If the graph is disconnected, traversal continues from each
     * remaining unvisited vertex.
     *
     * @param start the vertex where traversal begins
     */
    public void breadthFirstTraversal(int start) {
        checkVertex(start);

        // Records which vertices have already been visited.
        boolean[] visited = new boolean[vertexCount];

        // Traverse the component containing the requested start vertex.
        breadthFirstFrom(start, visited);

        // Visit other components if the graph is disconnected.
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                breadthFirstFrom(i, visited);
            }
        }

        System.out.println();
    }

    /**
     * Performs BFS within one connected or reachable component.
     *
     * @param start the first vertex for this traversal
     * @param visited the shared visited array
     */
    private void breadthFirstFrom(int start, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();

        // Mark the vertex before enqueueing it to avoid duplicate entries.
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            // Remove the next vertex in FIFO order.
            int current = queue.poll();
            visit(current);

            // Check every possible neighbor in the adjacency matrix.
            for (int next = 0; next < vertexCount; next++) {
                if (hasEdge(current, next) && !visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    /**
     * Performs depth-first traversal (DFS).
     *
     * DFS explores one path as deeply as possible before backtracking.
     * If the graph is disconnected, traversal also visits other components.
     *
     * @param start the vertex where traversal begins
     */
    public void depthFirstTraversal(int start) {
        checkVertex(start);

        boolean[] visited = new boolean[vertexCount];

        // Explore the component containing the starting vertex.
        depthFirstFrom(start, visited);

        // Continue with any remaining unvisited components.
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                depthFirstFrom(i, visited);
            }
        }

        System.out.println();
    }

    /**
     * Recursively performs DFS from the current vertex.
     *
     * @param current the vertex currently being explored
     * @param visited the shared visited array
     */
    private void depthFirstFrom(int current, boolean[] visited) {
        // Mark and process the current vertex before exploring its neighbors.
        visited[current] = true;
        visit(current);

        for (int next = 0; next < vertexCount; next++) {
            if (hasEdge(current, next) && !visited[next]) {
                depthFirstFrom(next, visited);
            }
        }
    }

    /**
     * Prints the connected components of an undirected graph.
     *
     * Each unvisited vertex starts a new component.
     * Directed graphs are not handled by this method.
     */
    public void printConnectedComponents() {
        if (directed) {
            System.out.println(
                    "This method supports undirected graphs only.");
            return;
        }

        boolean[] visited = new boolean[vertexCount];
        int componentCount = 0;

        // Every unvisited vertex belongs to a new component.
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                componentCount++;

                System.out.print("Component " + componentCount + ": ");

                // DFS marks every vertex in this component.
                depthFirstFrom(i, visited);

                System.out.println();
            }
        }

        System.out.println("Number of components: " + componentCount);
    }

    /**
     * Finds and prints one path from start to destination using DFS.
     *
     * The method does not guarantee the shortest path.
     *
     * @param start the starting vertex
     * @param destination the target vertex
     */
    public void printPath(int start, int destination) {
        checkVertex(start);
        checkVertex(destination);

        boolean[] visited = new boolean[vertexCount];

        // parent[v] stores the vertex used to reach v.
        // A value of -1 indicates that no parent has been assigned.
        int[] parent = new int[vertexCount];
        Arrays.fill(parent, -1);

        if (!findPathFrom(start, destination, visited, parent)) {
            System.out.println("No path found from "
                    + label(start) + " to " + label(destination));
            return;
        }

        // Reconstruct the path by following parent links backward.
        ArrayList<Integer> path = new ArrayList<>();
        int current = destination;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        // Parent links produce the path backward, so reverse the list.
        Collections.reverse(path);

        System.out.print("Path: ");
        printPathVertices(path);
    }

    /**
     * Uses recursive DFS to find a path to the destination.
     *
     * @param current the vertex currently being explored
     * @param destination the target vertex
     * @param visited the shared visited array
     * @param parent records the predecessor of each discovered vertex
     * @return true if the destination is reachable
     */
    private boolean findPathFrom(int current, int destination,
            boolean[] visited, int[] parent) {

        visited[current] = true;

        // The target has been reached.
        if (current == destination) {
            return true;
        }

        for (int next = 0; next < vertexCount; next++) {
            if (hasEdge(current, next) && !visited[next]) {

                // Record how the next vertex was reached.
                parent[next] = current;

                if (findPathFrom(next, destination, visited, parent)) {
                    return true;
                }
            }
        }

        // No path to the destination was found through this vertex.
        return false;
    }

    /**
     * Prints a path stored in a list of vertex indices.
     *
     * @param path the vertices in path order
     */
    private void printPathVertices(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(label(path.get(i)));

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();
    }

    /**
     * Finds the shortest path using Dijkstra's algorithm.
     *
     * Dijkstra requires non-negative edge weights.
     * The method prints the shortest distance and the corresponding path.
     *
     * @param start the starting vertex
     * @param destination the target vertex
     */
    public void findShortestPath(int start, int destination) {
        checkVertex(start);
        checkVertex(destination);

        // Dijkstra is not valid if any existing edge has a negative weight.
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (hasEdge(i, j) && matrix[i][j] < 0) {
                    System.out.println(
                            "Dijkstra cannot use negative edge weights.");
                    return;
                }
            }
        }

        // distance[v] stores the best distance found from start to v.
        int[] distance = new int[vertexCount];

        // parent[v] stores the previous vertex on the current best path.
        int[] parent = new int[vertexCount];

        // visited[v] is true once the shortest distance to v is finalized.
        boolean[] visited = new boolean[vertexCount];

        Arrays.fill(distance, NO_EDGE);
        Arrays.fill(parent, -1);

        // The distance from the starting vertex to itself is zero.
        distance[start] = 0;

        for (int count = 0; count < vertexCount; count++) {
            int current = -1;
            int minimum = NO_EDGE;

            // Select the unvisited vertex with the smallest known distance.
            for (int i = 0; i < vertexCount; i++) {
                if (!visited[i] && distance[i] < minimum) {
                    minimum = distance[i];
                    current = i;
                }
            }

            // No reachable unvisited vertex remains.
            if (current == -1) {
                break;
            }

            // The shortest distance to this vertex is now finalized.
            visited[current] = true;

            // Stop early when the destination is finalized.
            if (current == destination) {
                break;
            }

            // Try to improve the distances to all neighbors of current.
            for (int next = 0; next < vertexCount; next++) {
                if (hasEdge(current, next) && !visited[next]) {
                    int weight = matrix[current][next];

                    // Relax the edge if it gives a shorter route.
                    if (distance[current] + weight < distance[next]) {
                        distance[next] = distance[current] + weight;
                        parent[next] = current;
                    }
                }
            }
        }

        // NO_EDGE means the destination could not be reached.
        if (distance[destination] == NO_EDGE) {
            System.out.println("No path found.");
            return;
        }

        // Reconstruct the shortest path by following parent links.
        ArrayList<Integer> path = new ArrayList<>();
        int current = destination;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);

        System.out.println("Shortest distance: " + distance[destination]);
        System.out.print("Shortest path: ");
        printPathVertices(path);
    }

    /**
     * Finds an Euler circuit in an undirected graph.
     *
     * An Euler circuit starts and ends at the same vertex and uses
     * every edge exactly once. For a graph with edges, all vertices
     * with nonzero degree must belong to one connected component,
     * and every vertex must have even degree.
     *
     * This adjacency-matrix implementation does not support parallel
     * edges or self-loops.
     */
    public void findEulerCircuit() {
        if (directed) {
            System.out.println(
                    "This Euler implementation supports undirected graphs only.");
            return;
        }

        // remaining[i][j] indicates whether an edge is still unused.
        int[][] remaining = new int[vertexCount][vertexCount];

        // Used to check whether all vertices incident to edges are connected.
        boolean[] visited = new boolean[vertexCount];

        // Start at the first vertex that has at least one edge.
        int start = -1;

        // Check every vertex's degree and copy the existing edges.
        for (int i = 0; i < vertexCount; i++) {
            int degree = 0;

            for (int j = 0; j < vertexCount; j++) {
                if (hasEdge(i, j)) {
                    degree++;
                    remaining[i][j] = 1;
                }
            }

            // Every vertex in an Eulerian graph must have even degree.
            if (degree % 2 != 0) {
                System.out.println(
                        "No Euler circuit: a vertex has odd degree.");
                return;
            }

            if (degree > 0 && start == -1) {
                start = i;
            }
        }

        // An Euler circuit cannot be constructed if no edges exist.
        if (start == -1) {
            System.out.println("The graph has no edges.");
            return;
        }

        // Check connectivity starting from a vertex that has an edge.
        checkEulerConnectivity(start, remaining, visited);

        // Every vertex with an edge must have been reached.
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                for (int j = 0; j < vertexCount; j++) {
                    if (remaining[i][j] > 0) {
                        System.out.println(
                                "No Euler circuit: graph is disconnected.");
                        return;
                    }
                }
            }
        }

        // Hierholzer's algorithm builds an Euler circuit using a stack.
        Deque<Integer> stack = new ArrayDeque<>();

        // Vertices are added to this list when no unused edge remains.
        ArrayList<Integer> circuit = new ArrayList<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.peek();
            int next = 0;

            // Find the first unused edge leaving the current vertex.
            while (next < vertexCount
                    && remaining[current][next] == 0) {
                next++;
            }

            if (next == vertexCount) {

                // No unused edge remains, so add this vertex to the circuit.
                circuit.add(stack.pop());

            } else {

                // Mark the undirected edge as used in both directions.
                remaining[current][next] = 0;
                remaining[next][current] = 0;

                // Continue the circuit from the neighboring vertex.
                stack.push(next);
            }
        }

        // The vertices were collected in reverse order.
        Collections.reverse(circuit);

        System.out.print("Euler circuit: ");
        printPathVertices(circuit);
    }

    /**
     * Performs DFS to check connectivity for the Euler circuit algorithm.
     *
     * Only vertices connected by edges in the remaining-edge matrix
     * are visited.
     *
     * @param current the vertex currently being explored
     * @param remaining the matrix of edges used for the connectivity check
     * @param visited records visited vertices
     */
    private void checkEulerConnectivity(
            int current, int[][] remaining, boolean[] visited) {

        visited[current] = true;

        for (int next = 0; next < vertexCount; next++) {
            if (remaining[current][next] > 0 && !visited[next]) {
                checkEulerConnectivity(next, remaining, visited);
            }
        }
    }
}
