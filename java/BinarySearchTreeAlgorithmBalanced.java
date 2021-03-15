import java.util.Arrays;
import java.util.Random;

class Node{
    // AVL stores height alongwith value of node
    int value, height;
    Node left, right;

    public Node(int val){
        value = val;
        left = right = null;
    }
}

class BST{
    // Similar implementation of BST with new Node definition
    Node root;
    public BST(){
        root = null;
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
    // AVL is an entension of BST
    
    AVL(){

    }

    public void insert(int n){
        root = insertTree(root, n);
    }

    public Node insertTree(Node mid, int n){
        // Balances tree after value is inserted
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

    public void remove(int n){
        root = removeTree(root, n);
    }

    public Node removeTree(Node mid, int n){
        // Removes element and balances tree
        if(mid==null){
            return mid;
        }
        else if(n<mid.value){
            mid.left = removeTree(mid.left, n);
        }
        else if(n>mid.value){
            mid.right = removeTree(mid.right, n);
        }
        else{
            if(mid.left==null || mid.right==null){
                // Node has less than 2 child
                if(mid.left==null){
                    // If no left child then right will replace
                    mid = mid.right;
                }
                else{
                    // If no right child then left will replace
                    mid = mid.left;
                }
            }
            else{
                // Node has two childs
                Node greatestLeftChild = new Node(mid.right.value); // Smallest value in right subtree
                mid.value = greatestLeftChild.value;                // Replace node with that value
                mid.right = removeTree(mid.right, mid.value);       // Remove that smalles value
            }
            
        }
        if(mid!=null){
            // If there exists a node in AVL, rebalance it
            mid = balance(mid);
        }
        return mid;
    }

    public void updateHeight(Node mid){
        // Updates height property of node
        mid.height = 1 + Math.max(height(mid.left), height(mid.right));
    }

    public int height(Node mid){
        // Calculates height of node
        if(mid==null){
            return 0;
        }
        return mid.height;
    }

    public int balancingFactor(Node mid){
        // Calculates balancing factor a.k.a. height difference
        // Should be -1<=BF<=1
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
                    // If tree is right heavy
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
                    // If tree is left heavy
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
            // This is handle cases when the tree dosen't have enough values to performing rebalancing
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

        // Use this if you want to test rotation;
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