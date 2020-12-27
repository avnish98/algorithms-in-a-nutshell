import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class BucketSort {

    public static int Hash(int a){
        return (a/10);
    }

    public  static void Extract(Vector<Integer>[] B, int[] A){
        int index = 0;
        int n = A.length;

        for(int p=0;p<n;p++){
            Collections.sort(B[p]);
            for(int q=0;q<B[p].size();q++){
                A[index++] = B[p].get(q);
            }
        }

    }

    public static void Sort(int[] A){
        int n = A.length;
        Vector<Integer>[] B = new Vector[n];

        for(int i=0;i<n;i++){
            B[i] = new Vector<Integer>();
        }

        for(int i=0;i<n;i++){
            int k = Hash(A[i]);
            B[k].add(A[i]);
        }

        Extract(B, A);
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
        System.out.println("Array before sorting: "+ Arrays.toString(Arr));
        Sort(Arr);
        System.out.println("Array after sorting: "+ Arrays.toString(Arr));
    }
}
