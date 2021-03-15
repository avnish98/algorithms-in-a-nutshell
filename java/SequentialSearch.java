import java.util.Arrays;

public class SequentialSearch {
    public static int Search(int[] Arr, int target){
        int result = -1;
        for(int i =0;i<Arr.length;i++){
            if(Arr[i]==target){
                result = 1;
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
