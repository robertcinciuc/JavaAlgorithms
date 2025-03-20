package robertcinciuc.problems.leetcode.string;

public class BasicCalculator2 {

    public static class ExpressionResult{
        final int result;
        final int start;
        final int end;

        public ExpressionResult(int result, int start, int end) {
            this.result = result;
            this.start = start;
            this.end = end;
        }
    }

    public int calculate(String s) {
        String trimmed = trimSpaces(s);
        ExpressionResult[] calculations = new ExpressionResult[trimmed.length()];
        Integer lastValue = null;
//        Multiplication & division
        for(int i = 0; i < trimmed.length(); ++i){
            if(trimmed.charAt(i) == '*' || trimmed.charAt(i) == '/'){
                ExpressionResult a = getPrevExpression(calculations, i, trimmed);
                ExpressionResult b = getNextExpression(calculations, i, trimmed);
                int result = calculateExpression(a.result, b.result, trimmed.charAt(i));
                lastValue = result;
                i = markCalculation(calculations, result, a.start, b.end);
            }
        }

//        Addition & subtraction
        for(int i = 0; i < trimmed.length(); ++i){
            if(trimmed.charAt(i) == '+' || trimmed.charAt(i) == '-'){
                ExpressionResult a = getPrevExpression(calculations, i, trimmed);
                ExpressionResult b = getNextExpression(calculations, i, trimmed);
                int result = calculateExpression(a.result, b.result, trimmed.charAt(i));
                lastValue = result;
                i = markCalculation(calculations, result, a.start, b.end);
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

    public String getPrev(String s, int i){
        StringBuilder sb = new StringBuilder();
        i--;
        while(i >= 0 && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/'){
            sb.insert(0, s.charAt(i));
            i--;
        }
        return sb.toString();
    }

    public ExpressionResult getPrevExpression(ExpressionResult[] calculations, int i, String trimmed){
        ExpressionResult a;
        if(calculations[i - 1] != null){
            a = calculations[i - 1];
        }else{
            String element = getPrev(trimmed, i);
            a = new ExpressionResult(Integer.parseInt(element), i - element.length(), i);
        }
        return a;
    }

    public String getNext(String s, int i){
        StringBuilder sb = new StringBuilder();
        i++;
        while(i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/'){
            sb.append(s.charAt(i));
            i++;
        }
        return sb.toString();
    }

    public ExpressionResult getNextExpression(ExpressionResult[] calculations, int i, String trimmed){
        ExpressionResult b;
        if(calculations[i + 1] != null){
            b = calculations[i + 1];
        }else{
            String element = getNext(trimmed, i);
            b = new ExpressionResult(Integer.parseInt(element), i, i + element.length());
        }
        return b;
    }

    public int markCalculation(ExpressionResult[] calculations, Integer result, int start, int end){
        ExpressionResult expressionResult = new ExpressionResult(result, start, end);
        calculations[start] = expressionResult;
        calculations[end] = expressionResult;
        return end;
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
