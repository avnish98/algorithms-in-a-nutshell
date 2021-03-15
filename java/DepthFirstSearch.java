import java.util.Arrays;

class Graph{
    // Undirected Graph
    int[][] adjMatrix;
    int size;

    Graph(int n){
        // Initializes graph of n vertices
        size = n;

        // Initializing Adjacent Matrix
        // 1 if vertices are connected
        // 0 if vertices are disconnected
        adjMatrix = new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                adjMatrix[i][j] = 0;
            }
        }
    }

    public void showAdjMatrix(){
        // Displays Adjacent Matrix
        for(int[] r: adjMatrix){
            System.out.println(Arrays.toString(r));
        }
    }

    public void connect(int a, int b){
        // Connects vertices bidirectionally
        adjMatrix[a][b] = 1;
        adjMatrix[b][a] = 1;
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

public class DepthFirstSearch {
     
    // Predecessor array
    // Contains predecessor vertex of the element
    static int[] pred; 

    // Colors matrix
    // -1 : Unvisited vertex
    // 0 : Visited vertex but neighbors aren't visited
    // 1 : Visited vertex alogwith neighbors
    static int[] colors;
    
    // Number of vertices
    int n;

    DepthFirstSearch(int n){
        // Initialized predecessor and colors array
        pred = new int[n];
        colors = new int[n];
        for(int i=0;i<n;i++){
            pred[i] = (-1);
            colors[i] = (-1);
        }
    }

    public static void dfsVisit(Graph g, int u){
        // Tracks if the vertex is visited
        colors[u] = 0; // Marks vertex as visited
        int[] neighbors = g.neighbors(u); // List of neighbors of vertex
        for(int i=0;i<neighbors.length;i++){ // For each neighbor
            int neighbor = neighbors[i];   
            if(neighbor!=(-1) // Check if null
               && 
               colors[neighbor]==(-1) // Neighbor is unvisited
               ){
               pred[neighbor] = u;  // Add vertex as predecessor of neighbor
               dfsVisit(g, neighbor); // Visit neighbor (and its neighbors recursively)
            }
        }
        colors[u] = 1; // Mark vertex as visited + all neighbors visited
    }

    public static int graphPathSearch(Graph g, int source, int target){
        int result = -1;
        dfsVisit(g, source);
        if(
            colors[target]>-1 // If target isn't unvisited
        ){
            result = 1;
        }
        return result;
    }

    public static void main(String[] args) {

        Graph g = new Graph(14);
        
        // Connecting vertices
        g.connect(0, 1);
        g.connect(1, 3);
        g.connect(2, 10);
        g.connect(2, 11);
        g.connect(3, 12);
        g.connect(3, 4);
        g.connect(4, 13);
        g.connect(4, 5);
        g.connect(5, 9);
        g.connect(5, 6);
        g.connect(6, 7);
        g.connect(7, 8);
        g.connect(7, 9);
        g.connect(8, 13);
        
        // Success case
        DepthFirstSearch dfs = new DepthFirstSearch(14);
        int result = dfs.graphPathSearch(g, 0, 12);
        if(result==1){
            System.out.println("Path between 0 and 12 exists");
        }

        // Fail case
        DepthFirstSearch dfs2 = new DepthFirstSearch(14);
        result = dfs2.graphPathSearch(g, 2, 3);
        if(result==(-1)){
            System.out.println("Path between 2 and 3 dosen't exist");
         }
    }
}
