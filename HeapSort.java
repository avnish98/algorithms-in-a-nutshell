import java.util.Arrays;

public class HeapSort {

    public static void Swap(int[] A, int id1, int id2){
        int temp = A[id1];
        A[id1] = A[id2];
        A[id2] = temp;
    }

    public static void Heapify(int[] A, int id, int max){
        int largest = id;
        int left = 2*id + 1;
        int right = 2*id + 2;

        if((left<max)&&(A[left]<A[id])){
            largest = left;
        }

        if((right<max)&&(A[right]<A[largest])){
            largest = right;
        }

        if(largest != id){
            Swap(A, largest, id);
            Heapify(A, largest, max);
        }
    }

    public static void BuildHeap(int[] A){
        int n = A.length;
        for(int i = (n/2)-1; i>=0;i--){
            Heapify(A, i, n);
        }
    }

    public static void Sort(int[] A){
        BuildHeap(A);
        int n = A.length;
        for(int i=n-1; i>0; i--){
            Swap(A, 0, i);
            Heapify(A, 0 , i);
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
