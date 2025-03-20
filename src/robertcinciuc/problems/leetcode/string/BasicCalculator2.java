package robertcinciuc.problems.leetcode.string;

public class BasicCalculator2 {

    public int calculate(String s) {
        String trimmed = trimSpaces(s);
        Integer[] calculations = new Integer[trimmed.length()];
        Integer lastValue = null;
//        Multiplication & division
        for(int i = 0; i < trimmed.length(); ++i){
            if(trimmed.charAt(i) == '*' || trimmed.charAt(i) == '/'){
                int a = calculations[i - 1] != null ? calculations[i - 1] : getPrev(trimmed, i);
                int b = calculations[i + 1] != null ? calculations[i + 1] : getNext(trimmed, i);
                int result = calculateExpression(a, b, trimmed.charAt(i));
                lastValue = result;
                markCalculation(calculations, result, i - String.valueOf(a).length(), i + String.valueOf(b).length());
            }
        }

//        Addition & subtraction
        for(int i = 0; i < trimmed.length(); ++i){
            if(trimmed.charAt(i) == '+' || trimmed.charAt(i) == '-'){
                int a = calculations[i - 1] != null ? calculations[i - 1] : getPrev(trimmed, i);
                int b = calculations[i + 1] != null ? calculations[i + 1] : getNext(trimmed, i);
                int result = calculateExpression(a, b, trimmed.charAt(i));
                lastValue = result;
                markCalculation(calculations, result, i - String.valueOf(a).length(), i + String.valueOf(b).length());
            }
        }

        if(lastValue == null){
            return Integer.parseInt(trimmed);
        }

        return lastValue;
    }

    public String trimSpaces(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) != ' '){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public int getPrev(String s, int i){
        StringBuilder sb = new StringBuilder();
        i--;
        while(i >= 0 && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/'){
            sb.insert(0, s.charAt(i));
            i--;
        }
        return Integer.parseInt(sb.toString());
    }

    public int getNext(String s, int i){
        StringBuilder sb = new StringBuilder();
        i++;
        while(i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/'){
            sb.append(s.charAt(i));
            i++;
        }
        return Integer.parseInt(sb.toString());
    }

    public void markCalculation(Integer[] calculations, Integer result, int start, int end){
        for(int i = start; i <= end; ++i){
            calculations[i] = result;
        }
    }

    public int calculateExpression(int a, int b, char operation){
        if(operation == '*'){
            return a * b;
        }else if(operation == '/' && b != 0){
            return a / b;
        }else if(operation == '+'){
            return a + b;
        }else{
            return a - b;
        }
    }

    public static void main(String[] args){
        BasicCalculator2 calculator = new BasicCalculator2();
//        System.out.println(calculator.calculate("3+2*2"));
//        System.out.println(calculator.calculate("3/2"));
        System.out.println(calculator.calculate("1*2-3/4+5*6-7*8+9/10"));
    }
}
