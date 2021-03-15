import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionAlgorithm {
    public static void add (int[] n1, int[] n2, int[] sum) {
        int position = n1.length-1;
        int carry = 0;
        while (position >= 0) {
            int total = n1[position] + n2[position] + carry;
            sum[position+1] = total % 10;
            if (total > 9) { carry = 1; } else { carry = 0; }
            position--;
        }
        sum[0] = carry;
    }

    public static void plus(int[] n1, int[] n2, int[] sum) {
        int position = n1.length;
        int carry = 0;
        while (--position >= 0) {
            int total = n1[position] + n2[position] + carry;
            if (total > 9) {
                sum[position+1] = total-10;
                carry = 1;
            } else {
                sum[position+1] = total;
                carry = 0;
            }
        }
        sum[0] = carry;
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
        int[] c = new int[4];
        int[] d = new int[4];
        long startTime = System.nanoTime();
        add(a, b, c);
        long endTime = System.nanoTime();
        long duration = (long) ((endTime - startTime)/Math.pow(10, 6));
        System.out.println("Sum of 990 and 10 is :" + Arrays.toString(c));
        System.out.println("Time taken by modulo implementation" + duration);
        long startTime2 = System.nanoTime();
        plus(a, b, c);
        long endTime2 = System.nanoTime();
        long duration2 = (long) ((endTime2 - startTime2)/Math.pow(10, 6));
        System.out.println("Time taken by non modulo implementation" + duration2);
    }
}
