import java.util.Arrays;

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
        adjMatrix[b][a] = 1;
        weightMatrix[a][b] = weight;
        weightMatrix[b][a] = weight;
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

class DijkstraAlgorithmDense{
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

    DijkstraAlgorithmDense(int n){
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

    public static int[] shortestPath(Graph g, int source){
        // Returns an array of shortest distance from source to every other vertex

        dist[source] = 0; // Distance from source to source is 0

        while(true){
            int u = -1;
            int sd = 99;
            for(int i=0;i<pred.length;i++){ // For each node in graph
                if(!visited[i] // If node is unvisited 
                    && 
                    dist[i]<sd // Distance is less than infinity
                    ){  
                    sd = dist[i]; // Potential shortest distance
                    u = i;        // Select that node
                }
            }
            if(u==(-1)){ // If no node fulfills the condition break loop
                break;
            }
            visited[u] = true;
            for(
                int v:g.neighbors(u) // For each neighbor of u
            ){
                if(v!=(-1)){
                    int w = g.weightMatrix[u][v]; // Get current weight
                    if(v==u){
                        continue;
                    }
                    int newlength = dist[u]; // Distance from source to u
                    newlength += w;         // Distance(source, v) = Distance(source, u) = Distance(u+v)
                    if(newlength<dist[v]){  // If new distance is less than older distance
                        dist[v] = newlength; // Update it
                        pred[v] = u;
                    }
                }
            }
        }
        return dist; // Return array of distance from source
    }

    public static void main(String[] args) {

        Graph g = new Graph(5); // A non-directional graph
        DijkstraAlgorithmDense djks = new DijkstraAlgorithmDense(5);
        
        //Connecting vertices with weights
        g.connect(0, 1, 2);
        g.connect(0, 4, 4);
        g.connect(3, 0, 8);
        g.connect(1, 2, 3);
        g.connect(2, 3, 5);
        g.connect(4, 3, 7);
        g.connect(2, 4, 1);
    
        int[] dist = djks.shortestPath(g, 0);
        System.out.println("Shortest distance from source to other vertices: "+Arrays.toString(dist)); 
    }
}