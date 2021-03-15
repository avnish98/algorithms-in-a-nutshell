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

class FloydWarshall{
    // Predecessor array
    // Contains predecessor vertex of the element
    static int[][] pred; 

    // Distance array
    // Contains distance of individual vertex from source
    static int[][] dist;
    
    // Number of vertices
    int n;

    FloydWarshall(int n){
        // Initialized predecessor and distance array
        pred = new int[n][n];
        dist = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                pred[i][j] = (-1);
                if(i==j){
                    dist[i][j] = 0; 
                }
                else{
                    dist[i][j] = 99;
                }
            }
        }
    }

    public static void shortestPath(Graph g, int source) {
        // Returns an array of shortest distance from source to every other vertex

        // Fill distance matrix will weights of edges as distance
        for(int u=0;u<g.size;u++){
            for(int v=0;v<g.size;v++){
                for(int n:g.neighbors(v)){
                    if(n!=(-1) && g.weightMatrix[u][v] !=0){
                        dist[u][v] = g.weightMatrix[u][v];
                        pred[u][v] = u;
                    }
                }
            }
        }
        
        // Update distance if shortest path through some other vertex exists
        for(int k=0;k<g.size;k++){
            for(int u=0;u<g.size;u++){
                for(int v=0;v<g.size;v++){
                    int newlength = dist[u][k] + dist[k][v];
                    if(newlength<dist[u][v]){
                        dist[u][v] = newlength;
                        pred[u][v] = pred[k][v];
                    }
                }
            }
        }
    }

    public static void showDistanceMatrix(){
        for(int[] r: dist){
            System.out.println(Arrays.toString(r));
        }
    }

    public static void main(String[] args) {

        Graph g = new Graph(5); // A non-directional graph
        FloydWarshall flw = new FloydWarshall(5);
        
        //Connecting vertices with weights
        g.connect(0, 1, 2);
        g.connect(0, 4, 4);
        g.connect(3, 0, 8);
        g.connect(4, 3, 7);
        g.connect(1, 2, 3);
        g.connect(2, 4, 1);
        g.connect(2, 3, 5);

        
        flw.shortestPath(g, 0);
        
        System.out.println("Shortest distance from source to other vertices: ");
        flw.showDistanceMatrix(); 
    }
}