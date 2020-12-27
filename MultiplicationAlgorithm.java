import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplicationAlgorithm {
    public static void mult (int[] n1, int[] n2, int[] result) {
        int pos = result.length-1;
        Arrays.fill(result, 0);
        for (int m = n1.length-1; m>=0; m--) {
            int off = n1.length-1 - m;
            for (int n = n2.length-1; n>=0; n--,off++) {
                int prod = n1[m]*n2[n];
                result[pos-off] += prod % 10;
                result[pos-off-1] += result[pos-off]/10 + prod/10;
                result[pos-off] %= 10;
            }
        }
    }


    public static void main(String[] args) {
        int[] a = new int[3];
        a[0] = 9;
        a[1] = 9;
        a[2] = 0;
        int[] b = new int[3];
        b[0] = 0;
        b[1] = 1;
        b[2] = 0;
        int[] c = new int[10];
        long startTime = System.nanoTime();
        mult(a, b, c);
        long endTime = System.nanoTime();
        long duration = (long) ((endTime - startTime)/Math.pow(10, 6));
        System.out.println("Product of 990 and 10 is :" + Arrays.toString(c));
        System.out.println("Time taken by modulo implementation " + duration);
    }
}
