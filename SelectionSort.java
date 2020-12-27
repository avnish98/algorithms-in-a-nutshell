import java.util.Arrays;

public class SelectionSort {
    public static int MinPosition(int[] A, int start, int end){
        int minPos = start;
        for(int j=start+1; j<end; j++){
            if(A[j]<A[minPos]){
                minPos = j;
            }
        }
        return minPos;
    }

    public static void Sort(int[] A){
        int last = A.length -1;
        for(int i =0; i<last; i++){
            int minPos = MinPosition(A, i, last+1);
            int temp = A[minPos];
            A[minPos] = A[i];
            A[i] = temp;
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