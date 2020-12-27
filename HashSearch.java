import java.util.Arrays;
import java.util.Vector;

public class HashSearch {

    public static int Hash(int a){
        return (a/10);
    }

    public static int Search(int[] A, int target){
        int result = -1;
        int n = A.length;
        Vector<Integer>[] B = new Vector[n];

        for(int i=0;i<n;i++){
            B[i] = new Vector<Integer>();
        }

        for(int i=0;i<n;i++){ // Creating bins to store elements based on hash calculated
            int k = Hash(A[i]);
            B[k].add(A[i]);
        }

        int targetHash = Hash(target);

        Vector<Integer> searchHash = B[targetHash]; // Relevant bin based on hash calculated from target
        if(searchHash.contains(target)){
            result = 1;
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

        // Success case
        int result = Search(Arr, 9);
        if(result==1){
            System.out.println("9 is present in array");
        }

        // Fail case
        result = Search(Arr, 55);
        if(result==(-1)){
            System.out.println("55 is not present in array");
        }
    }
}
