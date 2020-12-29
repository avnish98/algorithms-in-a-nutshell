import java.util.Arrays;

class Queue{
    
    int[] values;
    int size;

    Queue(int n){
        size = n;
        values = new int[size];
        for(int i=0;i<n;i++){
            values[i] = (-1);
        }
    }

    void enqueue(int x){
        // Adds element to end
        for(int i=0;i<size;i++){
            if(values[i]==(-1)){
                values[i] = x;
                break;
            }
        }
    }

    void dequeue(){
        // Removes element from start
        if(isEmpty()!=1){

            for(int i=0;i<size-1;i++){
                values[i] = values[i+1];
            }

            values[size-1] = (-1);
        }
    }

    int isEmpty(){
        // Checks if queue is empty
        if(values[0]==(-1)){
            return 1;
        }
        return (-1);
    }

    int head(){
        // Returns first element of queue
        if(isEmpty()!=1){
            return values[0];
        }
        return -1;
    }
}

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

public class BreadthFirstSearch {
     
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

    // Queue to store vertices to visit
    static Queue q;

    BreadthFirstSearch(int n){
        // Initialized predecessor and colors array
        pred = new int[n];
        colors = new int[n];
        q = new Queue(n);
        for(int i=0;i<n;i++){
            pred[i] = (-1);
            colors[i] = (-1);
        }
    }

    public static void bfsVisit(Graph g, Queue q){
        // Visits each vertex and its neighbors
        while(q.isEmpty()!=(1)){
            // Select first element from queue
            int u = q.head();

            // For each neighbor of vertex
            for(int i : g.neighbors(u)){
                if(
                    i!=(-1)  // If element isn't null
                    && 
                    colors[i]==(-1) // If element is unvisited
                    ){
                    pred[i] = u; // Add u as predecessor
                    colors[i] = 0; // Set color as visited
                    q.enqueue(i); // Add vertex to queue
                }
               
            }
            q.dequeue(); // Remove first element after visiting its vertex
            colors[u] = 1; // Vertex and its neighbors visited
        }
    }

    public static int graphPathSearch(Graph g, Queue q, int source, int target){
        int result = -1;
        
        // Visiting source
        colors[source] = 0;

        // Add source to queue
        q.enqueue(source);
        
        // Visit source and other neighbor vertices (recursively)
        bfsVisit(g, q);
        
        if(
            colors[target]>-1 // If target isn't unvisited
        ){
            result = 1;
        }
        return result;
    }

    public static void main(String[] args) {

        Graph g = new Graph(14);
        Queue q = new Queue(14);
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
        BreadthFirstSearch dfs = new BreadthFirstSearch(14);
        int result = dfs.graphPathSearch(g, q, 0, 12);
        if(result==1){
            System.out.println("Path between 0 and 12 exists");
        }

        // Fail case
        Queue q2  = new Queue(14);
        BreadthFirstSearch dfs2 = new BreadthFirstSearch(14);
        result = dfs2.graphPathSearch(g, q2, 2, 3);
        if(result==(-1)){
            System.out.println("Path between 2 and 3 dosen't exist");
         }
    }
}
