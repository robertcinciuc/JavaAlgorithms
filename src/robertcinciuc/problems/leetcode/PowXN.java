package robertcinciuc.problems.leetcode;

public class PowXN {
    public double myPow(double x, int n) {
        if(n == 0){
            return 1;
        }
        double result = 1;
        for(int i = 0; i < Math.abs(n); i ++){
            result = result * x;
        }
        if(n < 0){
            return 1.0 / result;
        }
        return result;
    }

    public static void main(String[] args){
        PowXN powXN = new PowXN();
        System.out.println(powXN.myPow(2, 4));
    }
}
