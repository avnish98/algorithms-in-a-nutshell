class Reverser {
    public static void reverseString(char[] s) {
        int last = s.length -1;
        for(int i=0;i<s.length;i++){
            char temp = s[i];
            s[i] = s[last];
            s[last] =  temp;
            last--;
        }
        System.out.println(s);
    }

    public static void main(String[] args) {
        char[] s = new char[5];
        s[0] = 'h';
        s[1] = 'e';
        s[2] = 'l';
        s[3] = 'l';
        s[4] = 'o';
        reverseString(s);
    }
}

