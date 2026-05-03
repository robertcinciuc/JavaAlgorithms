package robertcinciuc.problems.leetcode.bitshift;

public class PowXN {
    public double myPow(double x, int n) {
        if(n == 0){
            return 1;
        }
        double result = 1;
        int n1 = Math.abs(n);
        while(n1 != 0){
            if((n1 & 1) != 0){
                result *= x;
            }
            x *= x;
            n1 >>>= 1;
        }

        if(n < 0){
            return  1.0 / result;
        }

        return result;
    }

    public static void main(String[] args){
        PowXN powXN = new PowXN();
        System.out.println(powXN.myPow(2, 4));
    }
}
