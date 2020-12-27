import java.util.Arrays;

public class InsertionSort {
    public static void Insert(int[] A, int pos, int value){
        int i = pos - 1;
        while((i>=0)&&(A[i]>value)){
            A[i+1] = A[i];
            i--;
        }
        A[i+1] = value;

    }

    public static void Sort(int[] A){
        for(int pos=1;pos<A.length;pos++){
            Insert(A, pos, A[pos]);
        }
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
