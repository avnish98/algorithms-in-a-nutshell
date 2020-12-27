import java.util.Arrays;

public class QuickSort {

    public static void Swap(int[] A, int id1, int id2){
        int temp = A[id1];
        A[id1] = A[id2];
        A[id2] = temp;
    }

    public static int Partition(int[] A, int left, int right){
        int pivot = A[right];
        int i = left-1; // Last position of smaller element pointer
        for(int j=left;j<=right-1;j++){ // j is loop pointer
            if(A[j]<pivot){
                i++; // There is another smaller element so increase position by 1
                Swap(A, i, j); // Put that element in the new position
            }
        }
        Swap(A, i+1, right); // Put pivot where it belongs i.e. middle
        return (i+1); // Location of pivot
    }

    public static void QuickSort(int[] A, int left, int right){
        if(left<right){
            int pivot = Partition(A, left, right);
            QuickSort(A, left, pivot-1);
            QuickSort(A, pivot+1, right);
        }
    }

    public static void Sort(int[] A){
        QuickSort(A, 0, A.length-1);
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
