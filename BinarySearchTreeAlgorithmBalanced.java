import java.util.Arrays;
import java.util.Random;

class Node{
    int value, height;
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

class AVL extends BST{
    
    AVL(){

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
        return balance(mid);
    }

    public void updateHeight(Node mid){
        mid.height = 1 + Math.max(height(mid.left), height(mid.right));
    }

    public int height(Node mid){
        if(mid==null){
            return 0;
        }
        return mid.height;
    }

    public int balancingFactor(Node mid){
        if(mid==null){
            return 0;
        }
        return height(mid.left) - height(mid.right);
    }

    public Node rotateRight(Node Y){    
        // Right (Clockwise) Rotation
        //             Y        X
        //            / \      /  \ 
        //           X   &     $   Y 
        //         /  \           / \
        //        $    Z         Z   &

        System.out.println("Rotating right");
        Node X = Y.left;
        Node Z = X.right;

        X.right = Y;
        Y.left = Z;

        updateHeight(X);
        updateHeight(Y);
        return X;
    }

    public Node rotateLeft(Node Y){    
        // Left (Anticlockwise) Rotation
        //             Y            X
        //            / \         /  \ 
        //           $   X       Y   &
        //             /  \     / \ 
        //            Z    &   $   Z
        
        System.out.println("Rotating left");
        Node X = Y.right;
        Node Z = X.left;

        X.left = Y;
        Y.right = Z;

        updateHeight(X);
        updateHeight(Y);
        return X;
    }
    
    public Node balance(Node Z){
        updateHeight(Z);
        int balance = balancingFactor(Z);
        try{
            if(balance>1){
                if(height(Z.right.right)>height(Z.right.left)){
                    Z = rotateLeft(Z);
                }
                else{
                    // Right-Left Rotation
                    //             Z                Z                X
                    //            / \              / \             /   \
                    //           $   Y            $   X           Z     Y
                    //              / \              / \         / \   / \
                    //             X   @            &   Y       $   & *   @
                    //            / \                  / \
                    //           &   *                *   @
                    
                    Z.right = rotateRight(Z.right);
                    Z = rotateLeft(Z);
                }
            }
            else if(balance<(-1)){
                if(height(Z.left.left)>height(Z.left.right)){
                    Z = rotateRight(Z);
                }
                else{
                    // Left-Right Rotation
                    //             Z                Z                 X           
                    //            / \              / \             /    \  
                    //           Y   $            X   $           Y      Z
                    //          / \              / \             / \   /  \   
                    //         @   X            Y   &           @   * &    $
                    //            / \          / \   
                    //           *   &        @   *  

                    Z.left = rotateLeft(Z.left);
                    Z = rotateRight(Z);
                }
            }
        }
        catch(NullPointerException e){
            //System.out.println(e);
        }
        return Z;
    }
}

class BinarySearchTreeAlgorithmBalanced{

    public static void createTree(int[] A, AVL avl){
        for(int i=0; i<A.length; i++){
            avl.insert(A[i]);
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

    public static int Search(int[] Arr, int target, AVL avl){
        createTree(Arr, avl);
        int result = 1;
        Node resultNode = SearchTree(avl.root, target);
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
        Arr[8] = 15;
        Arr[9] = 5;

        // Random rand = new Random();
        // for(int i=0;i<Arr.length;i++){
        //     Arr[i] = rand.nextInt(100);
        // }

        System.out.println("Array: "+ Arrays.toString(Arr));

        AVL avl = new AVL();
        
        // Success case
        int result = Search(Arr, 9, avl);
        if(result==1){
            System.out.println("9 is present in array");
        }

        AVL avl2 = new AVL();
        
        // Fail case
        result = Search(Arr, 55, avl2);
        if(result==(-1)){
            System.out.println("55 is not present in array");
        }
    }
}