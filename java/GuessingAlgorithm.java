// Number of turns calculator for guessing problem

public class GuessingAlgorithm {

    public static int turns(int n, // Number to guess
                     int high, int low){
        int turns = 0;
        while(high>=low){
            turns++;
            int mid = (low+high)/2;
            if(mid==n){         // Number is the same as mid
                return turns;
            }
            else if(mid<n){     // Number is in lower half of range
                low = mid +1;   // shift lower to mid + 1
            }
            else{               // Number is in upper half of range
                high = mid -1; //  shift high to mid - 1
            }
        }
        return turns;
    }

    public static void main(String[] args){
        System.out.println("Number of turns required to find a guessed number between 1 and 1000000 is");
        System.out.println(turns(500, 1000000, 1));
    }
}
