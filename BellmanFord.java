import java.util.Arrays;
import java.lang.Exception;

class NegativeCycleException extends Exception{
    NegativeCycleException(String errorMsg){
        super(errorMsg);
    }
}

class Graph{
    // Undirected Graph
    int[][] adjMatrix;
    int size;
    int[][] weightMatrix;

    Graph(int n){
        // Initializes graph of n vertices
        size = n;

        // Initializing Adjacent Matrix and Weights Matrix (with all 0s)
        // 1 if vertices are connected
        // 0 if vertices are disconnected
        adjMatrix = new int[size][size];
        weightMatrix = new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                adjMatrix[i][j] = 0;
                weightMatrix[i][j] = 0;
            }
        }
    }

    public void showAdjMatrix(){
        // Displays Adjacent Matrix
        for(int[] r: adjMatrix){
            System.out.println(Arrays.toString(r));
        }
    }

    public void showWeightsMatrix(){
        // Displays Weights Matrix
        for(int[] r: weightMatrix){
            System.out.println(Arrays.toString(r));
        }
    }

    public void connect(int a, int b, int weight){
        // Connects vertices bidirectionally
        adjMatrix[a][b] = 1;
        //adjMatrix[b][a] = 1;
        weightMatrix[a][b] = weight;
        //weightMatrix[b][a] = (-1)*weight;
    }

    public int[] neighbors(int a){
        // Returns list of neighbors
        // (-1) is subtitute for null values
        int[] neighbors = new int[size];
        
        for(int i=0;i<size;i++){
            neighbors[i] = (-1);
        }

        int iter =0, i=0;
        while(i<size){
            if(adjMatrix[a][i]==1){
                neighbors[iter] = i;
                iter++;
            }
            i++;
        }
        return neighbors;
    }
}

class BellmanFord{
    // Predecessor array
    // Contains predecessor vertex of the element
    static int[] pred; 

    // Distance array
    // Contains distance of individual vertex from source
    static int[] dist;
    
    // Visited boolean array
    // Contains a boolen value to represent visited/unvisited vertices
    static boolean[] visited;

    // Number of vertices
    int n;

    BellmanFord(int n){
        // Initialized predecessor and distance array
        pred = new int[n];
        dist = new int[n];
        visited = new boolean[n];
        for(int i=0;i<n;i++){
            pred[i] = (-1);
            dist[i] = 99; // Substitute for postitive infinity
            visited[i] = false;
        }
    }

    public static int[] shortestPath(Graph g, int source) throws NegativeCycleException{
        // Returns an array of shortest distance from source to every other vertex

        dist[source] = 0; // Distance from source to source is 0

        int n = g.size;
        for(int i=1;i<n+1;i++){
            for(int u=0;u<n;u++){
                for(int v=0;v<n;v++){
                    if(g.adjMatrix[u][v]!=0){
                        int w = g.weightMatrix[u][v];
                        int newlength = dist[u] + w;
                        if(newlength<dist[v]){
                            if(i==n){
                                throw new NegativeCycleException("Negative Cycle exists in graph");
                            }
                            dist[v] = newlength;
                            pred[v] = i;
                        }
                    }
                }
            }
                    

        }
        return dist; // Return array of distance from source
    }

    public static void main(String[] args) {

        Graph g = new Graph(5); // A non-directional graph
        BellmanFord blf = new BellmanFord(5);
        
        //Connecting vertices with weights
        g.connect(0, 4, 2);
        g.connect(4, 3, 4);
        g.connect(4, 1, 5);
        g.connect(1, 3, -2);
        g.connect(3, 2, 6);
        g.connect(2, 1, -3);

        try{
            int[] dist = blf.shortestPath(g, 0);
        }
        catch(Exception m){
            System.out.println("Exception occured: "+m);
        }
        System.out.println("Shortest distance from source to other vertices: "+Arrays.toString(dist)); 
    }
}