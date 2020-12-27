import java.util.Arrays;

public class BinarySearch {
    public static int Search(int[] Arr, int high, int low,  int target){
        int result = -1;
        int mid;
        while(low<=high) {
            mid = (high + low) / 2;
            if (target < Arr[mid]) {
                high = mid-1; // Search in left sub-array
            } else if (target > Arr[mid]) {
                low = mid+1; // Search in right sub-array
            } else{
                result = 1; // Element found
                break;
            }
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
        MergeSort.Sort(Arr);
        System.out.println("Array: "+ Arrays.toString(Arr));

        // Success case
        int result = Search(Arr, Arr.length-1, 0, 9);
        if(result==1){
            System.out.println("9 is present in array");
        }

        // Fail case
        result = Search(Arr, Arr.length-1, 0, 55);
        if(result==(-1)){
            System.out.println("55 is not present in array");
        }
    }
}
