package parser;

import network.Network;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NetworkParser {
    public static Network parseFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        int numNodes = Integer.parseInt(br.readLine().trim());
        Network network = new Network(numNodes);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            int capacity = Integer.parseInt(parts[2]);
            network.addEdge(from, to, capacity);
        }
        br.close();
        return network;
    }
}