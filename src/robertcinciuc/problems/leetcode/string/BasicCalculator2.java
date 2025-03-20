package robertcinciuc.problems.leetcode.string;

import java.util.Stack;

public class BasicCalculator2 {

    public static class Element{
        public final int end;
        public int value;

        public Element(int value, int end) {
            this.end = end;
            this.value = value;
        }
    }

    public int calculate(String s) {
        String trimmed = trimSpaces(s);
        Stack<Element> sumStack = new Stack<>();
        int sign = 1;
        int i = 0;
        while(i < trimmed.length()){
            if(trimmed.charAt(i) == '+' || trimmed.charAt(i) == '-' || trimmed.charAt(i) == '*' || trimmed.charAt(i) == '/'){
                if(trimmed.charAt(i) == '-'){
                    sign = -1;
                    i++;
                }else if(trimmed.charAt(i) == '+'){
                    sign = 1;
                    i++;
                }else{
                    Element last = sumStack.pop();
                    Element next = getNext(trimmed, i + 1);

                    if(trimmed.charAt(i) == '*'){
                        next.value = last.value * next.value;
                    }else{
                        next.value = last.value / next.value;
                    }
                    sumStack.add(next);
                    i = next.end;
                }
            }else{
                Element elem = getNext(trimmed, i);
                elem.value *= sign;
                sumStack.add(elem);
                i = elem.end;
            }
        }

        int result = 0;
        while(sumStack.size() > 0){
            result += sumStack.pop().value;
        }

        return result;
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


    public Element getNext(String s, int i){
        StringBuilder sb = new StringBuilder();
        while(i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/'){
            sb.append(s.charAt(i));
            i++;
        }
        return new Element(Integer.parseInt(sb.toString()), i);
    }

    public static void main(String[] args){
        BasicCalculator2 calculator = new BasicCalculator2();
        System.out.println(calculator.calculate("3+2*2"));
        System.out.println(calculator.calculate("3/2"));
        System.out.println(calculator.calculate("1*2-3/4+5*6-7*8+9/10"));
    }
}
