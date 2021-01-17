//Prim's Algorithm: Finds Minimum Spanning Tree in a Graph

import java.util.Arrays;

class PriorityQueue{
    int[] values;
    int[] priorities;
    int size;

    PriorityQueue(int n){
        size = n;
        values = new int[size];
        priorities = new int[size];
        for(int i=0;i<n;i++){
            values[i] = (-1);
            priorities[i] = (-1);
        }
    }

    boolean contains(int x){
        for(int i=0;i<size;i++){
            if(values[i]==x){
                return true;
            }
        }
        return false;
    }

    void enqueue(int x, int p){
        // Adds element to end
        for(int i=0;i<size;i++){
            if(values[i]==(-1)){
                values[i] = x;
                priorities[i] = p;
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

    void remove(int v){
        // Removes element v from priority queue
        if(isEmpty()!=1){
            for(int i=0;i<size;i++){
                if(values[i]==v){
                    int j = i;
                    while((j+1)!=size){
                        values[j] = values[j+1];
                        j++;

                    }
                    values[j] = (-1);
                    break;
                }
            }
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

    int minPriority(){
        // Returns element with minimum priority
        if (priorities.length == 0)
            return -1;

        int index = 0;
        int min = priorities[index];

        for (int i = 1; i < priorities.length; i++){
            if ((priorities[i]!=(-1))&&(priorities[i]<min)){
                min = priorities[i];
                index = i;
            }
        }
        return values[index];
    }

    void changePriority(int x, int p){
        // Changes priority of element x to p
        priorities[x] = p;
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

class PrimAlgorithm{
    // Predecessor array
    // Contains predecessor vertex of the element
    static int[] pred; 

    // Key array
    // Contains priority of each vertex
    static int[] key;
    
    // Number of vertices
    int n;

    PrimAlgorithm(int n){
        // Initialized predecessor and MST array
        pred = new int[n];
        key = new int[n];
        for(int i=0;i<n;i++){
            pred[i] = (-1);
            key[i] = 99; // Substitute for postitive infinity
        }
    }

    public static int[] findMST(Graph g, PriorityQueue pq, int source){
        // Returns Minimum Spanning Tree from Graph

        key[source] = 0;
        
        for(int i=0;i<g.size;i++){
            pq.enqueue(i, key[i]); // Add all elements to priority queue with associated priority
        }

        while(pq.isEmpty()!=(1)){
            int u = pq.minPriority(); // Element with highest priority
            int iter = 0; // Other vertex v
            while(iter<g.size){
                if(pq.contains(iter)){ // If priority queue contains other vertex v
                    int w = g.weightMatrix[u][iter]; // Weight of edge (u, v)
                    if(w!=0 && w<key[iter]){ // If there is an edge and weight of that edge is smaller than existing edge in MST
                        pred[iter] = u; // Update predecessor of v
                        key[iter] = w; // Add that edge to MST
                        pq.changePriority(iter, w); // Change priority of v to w
                    }
                }
                iter++; // Next edge
            }
            pq.remove(u); // Remove element from priority queue once finished with all neighbors
        }
        return pred; // Return array containing MST
    }

    public static void main(String[] args) {

        Graph g = new Graph(5); // A non-directional graph
        PriorityQueue pq = new PriorityQueue(5); // A priority Queue
        PrimAlgorithm prim = new PrimAlgorithm(5);
        
        //Connecting vertices with weights
        g.connect(0, 1, 2);
        g.connect(0, 4, 4);
        g.connect(0, 3, 8);
        g.connect(1, 2, 3);
        g.connect(2, 4, 1);
        g.connect(2, 3, 5);
        g.connect(3, 4, 7);
    
        int[] mst = prim.findMST(g, pq, 0);
        System.out.println("Minimum Spanning Tree in Graph: "+Arrays.toString(mst)); 
    }
}