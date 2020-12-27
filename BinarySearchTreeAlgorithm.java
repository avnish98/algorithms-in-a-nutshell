import java.util.Arrays;

class Node{
    int value;
    Node left, right;

    public Node(int val){
        value = val;
        left = right = null;
    }
}

class BST{

    Node root;
    public BST(){
        root = null;
    }

    public void insert(int n){
        root = insertTree(root, n);
    }

    public Node insertTree(Node mid, int n){
        if(mid==null){
            mid = new Node(n);
            return mid;
        }
        else if(n<mid.value){
            mid.left = insertTree(mid.left, n);
        }
        else if(n>mid.value){
            mid.right = insertTree(mid.right, n);
        }
        return mid;
    }

    public void traverse(){
        traverseTree(root);
    }

    public void traverseTree(Node mid){
        if(mid!=null){
            traverseTree(mid.left);
            System.out.println(mid.value);
            traverseTree(mid.right);
        }
    }
}

class BinarySearchTreeAlgorithm{

    public static void createTree(int[] A, BST bst){
        for(int i=0; i<A.length; i++){
            bst.insert(A[i]);
        }
    }

    public static Node SearchTree(Node mid, int target){
        if(mid==null || mid.value==target){
            return mid;
        }
        else if(target<mid.value){
            return SearchTree(mid.left, target);
        }
        else if(target>mid.value){
            return SearchTree(mid.right, target);
        }
        return null;
    }

    public static int Search(int[] Arr, int target, BST bst){
        createTree(Arr, bst);
        int result = 1;
        Node resultNode = SearchTree(bst.root, target);
        if(resultNode==null){
            result = -1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] Arr = new int[10];
        Arr[0] = 99;
        Arr[1] = 9;
        Arr[2] = 56;
        Arr[3] = 78;
        Arr[4] = 12;
        Arr[5] = 18;
        Arr[6] = 76;
        Arr[7] = 19;
        Arr[8] = 12;
        Arr[9] = 5;

        System.out.println("Array: "+ Arrays.toString(Arr));

        BST bst = new BST();
        
        // Success case
        int result = Search(Arr, 9, bst);
        if(result==1){
            System.out.println("9 is present in array");
        }

        BST bst2 = new BST();
        // Fail case
        result = Search(Arr, 55, bst2);
        if(result==(-1)){
            System.out.println("55 is not present in array");
        }
    }
}