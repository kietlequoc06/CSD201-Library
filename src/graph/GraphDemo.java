package graph;

import java.util.Scanner;

/**
 * Demonstrates graph creation and graph algorithms.
 */
public class GraphDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a weighted, undirected graph with six vertices.
        Graph graph = new Graph(6, true, false);

        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 4);
        graph.addEdge(4, 5, 2);
        graph.addEdge(5, 0, 6);

        int choice;

        do {
            System.out.println("\n===== GRAPH MENU =====");
            System.out.println("1. Print adjacency matrix");
            System.out.println("2. Add edge");
            System.out.println("3. Remove edge");
            System.out.println("4. Breadth-first traversal (BFT)");
            System.out.println("5. Depth-first traversal (DFT)");
            System.out.println("6. Connected components");
            System.out.println("7. Find a path");
            System.out.println("8. Dijkstra shortest path");
            System.out.println("9. Euler circuit");
            System.out.println("10. Create a new graph");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    graph.printMatrix();
                    break;

                case 2: {
                    System.out.print("Enter start vertex (A-Z): ");
                    int from = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    System.out.print("Enter end vertex (A-Z): ");
                    int to = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    if (graph.isWeighted()) {
                        System.out.print("Enter edge weight: ");
                        int weight = scanner.nextInt();
                        graph.addEdge(from, to, weight);
                    } else {
                        graph.addEdge(from, to);
                    }

                    System.out.println("Edge added.");
                    break;
                }

                case 3: {
                    System.out.print("Enter start vertex (A-Z): ");
                    int from = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    System.out.print("Enter end vertex (A-Z): ");
                    int to = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    graph.removeEdge(from, to);
                    System.out.println("Edge removed.");
                    break;
                }

                case 4: {
                    System.out.print("Enter starting vertex (A-Z): ");
                    int start = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    graph.breadthFirstTraversal(start);
                    break;
                }

                case 5: {
                    System.out.print("Enter starting vertex (A-Z): ");
                    int start = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    graph.depthFirstTraversal(start);
                    break;
                }

                case 6:
                    graph.printConnectedComponents();
                    break;

                case 7: {
                    System.out.print("Enter start vertex (A-Z): ");
                    int start = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    System.out.print("Enter destination vertex (A-Z): ");
                    int destination = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    graph.printPath(start, destination);
                    break;
                }

                case 8: {
                    System.out.print("Enter start vertex (A-Z): ");
                    int start = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    System.out.print("Enter destination vertex (A-Z): ");
                    int destination = Character.toUpperCase(
                            scanner.next().charAt(0)) - 'A';

                    graph.findShortestPath(start, destination);
                    break;
                }

                case 9:
                    graph.findEulerCircuit();
                    break;

                case 10: {
                    System.out.print("Number of vertices (1-26): ");
                    int count = scanner.nextInt();

                    if (count < 1 || count > 26) {
                        System.out.println(
                                "Please enter a number from 1 to 26.");
                        break;
                    }

                    System.out.print("Weighted graph? (true/false): ");
                    boolean weighted = scanner.nextBoolean();

                    System.out.print("Directed graph? (true/false): ");
                    boolean directed = scanner.nextBoolean();

                    graph = new Graph(count, weighted, directed);

                    System.out.println(
                            "New graph created. Add edges using option 2.");
                    break;
                }

                case 0:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
