
import algorithm.Algorithm;
import algorithm.MaxFlow;
import network.Network;
import network.Edge;
import parser.NetworkParser;


import java.io.File;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            String folderPath = "input/"; 

            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            if (listOfFiles == null || listOfFiles.length == 0) {
                System.out.println("No input files found in the directory.");
                return;
            }

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println("\n--------------------------------------------");
                    System.out.println("Processing file: " + file.getName());

                    // Parse the network
                    Network network = NetworkParser.parseFromFile(file.getAbsolutePath());
                    int source = 0;
                    int sink = network.size() - 1;

                    MaxFlow algorithm = new Algorithm();

                    // Measure execution time
                    long startTime = System.nanoTime();
                    int maxFlow = algorithm.findMaxFlow(network, source, sink);
                    long endTime = System.nanoTime();

                    double elapsedTimeMs = (endTime - startTime) / 1_000_000.0; 

                    // Print results
                    System.out.printf("File: %-20s | Max Flow: %-8d | Time: %.2f ms%n",
                            file.getName(), maxFlow, elapsedTimeMs);

                    System.out.println("Final flow network:");
                    for (var edges : network.getGraph()) {
                        for (Edge e : edges) {
                            if (e.capacity > 0) { 
                                System.out.println(e);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input files: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Error: " + e.getMessage());
        }
    }
}
