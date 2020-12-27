import java.util.Arrays;

public class BloomFilterSearch{

    public static int Hash1(int X){
        return X%10;
    }

    public static int Hash2(int X){
        return X%57;
    }

    public static int Hash3(int X){
        return X%43;
    }

    public static int SelectHash(int X, int Y){
        int hash=0;
        if(Y==0){
            hash = Hash1(X);
        }
        else if(Y==1){
            hash = Hash2(X);
        }
        else if(Y==2){
            hash = Hash3(Y);
        }
        return hash;
    }

    public static int[] createArr(int Size, int loc){
        int[] tempArr = new int[Size];
        for(int i =0;i<Size;i++){
            if(i==loc){
                tempArr[i] = 1;
            }
        }
        return tempArr;
    }

    public static int[] ORArray(int[] A, int[] B){
        int[] C = new int[A.length];
        for(int i=0;i<A.length;i++){
            C[i] = A[i] | B[i];
        }
        return C;
    }

    public static int[] ANDArray(int[] A, int[] B){
        int[] C = new int[A.length];
        for(int i=0;i<A.length;i++){
            C[i] = A[i] & B[i];
        }
        return C;
    }

    public static int contains1(int[] A){
        int result = 0;
        for(int i=0;i<A.length;i++){
            if(A[i]==1){
                result =1;
                break;
            }
        }
        return result;
    }

    public static int[] createBitArray(int[] Arr){
        int[] BitArr = new int[300];
        for(int i =0; i<BitArr.length; i++){
            BitArr[i] = 0;
        }

        int hashVal;
        int[] hashArr;
        for(int i=0;i<Arr.length;i++){
            for(int j=0;j<3;j++){
                hashVal = SelectHash(Arr[i], j);
                hashArr = createArr(300, hashVal);
                BitArr = ORArray(hashArr, BitArr);
            }
        }

        return BitArr;
    }

    public static int Search(int[] Arr, int target){
        int result = -1;
        int[] BitArr = createBitArray(Arr);

        int hashMatch = 0;
        int hashVal;
        int[] checkArr;
        for(int j=0;j<3;j++){
            hashVal = SelectHash(target, j);
            checkArr = createArr(300, hashVal);
            if(contains1(ANDArray(BitArr, checkArr))==1){
                hashMatch+=1;
            }
        }

        if(hashMatch==3){
            result=1;
        }

        System.out.println("\n"+"Target: "+target+"\nHashmatches (out of 3): "+hashMatch);
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