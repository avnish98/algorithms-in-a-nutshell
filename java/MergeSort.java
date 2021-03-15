import java.util.Arrays;

public class MergeSort {

    public static void Swap(int[] Arr, int a, int b){
        int temp = Arr[a];
        Arr[a] = Arr[b];
        Arr[b] = temp;
    }

    public static void mergeSort(int[] A, int[] B, int start, int end){
        if((end-start)<2){ // Only one element in array; return as is
            return;
        }
        if((end-start)==2){ // Two elements in array check if they are unsorted
            if(B[start]>B[start+1]){ // If unsorted, sort them by swapping
                Swap(B, start, start+1);
            }
        }
        int mid = (start + end)/2; // Middle of array
        mergeSort(B, A, start, mid); // Merge sort on left half
        mergeSort(B, A, mid, end); // Merge sort on right half

        int i = start; // Pointer on left array
        int j = mid;   // Pointer on right array
        int idx = start; // Loop variable
        while(idx<end){
            if(
                    (j>=end) // Pointer has reached at end
                    || 
                    (
                            (i<mid) // Pointer hasn't crossed mid (or end of left array)
                            &&
                            (A[i]<A[j]) // There is no smaller element in right half
                    )
            ){
                B[idx] = A[i]; // Add element from left half to array
                i++;
            }
            else{
                B[idx] = A[j]; // Add element from right half to array
                j++;
            }
            idx++;
        }
    }

    public static void Sort(int[] A){
        int[] copy= A.clone();
        mergeSort(copy, A, 0, A.length);
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
