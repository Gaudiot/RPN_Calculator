import java.util.Scanner;
import java.util.Stack;

/*
Exemplo de entrada:
10
10
+
Saida: 20
*/

public class Calculator {
    static boolean isOperator(String string){
        if(string.equals("+")) return true;
        if(string.equals("*")) return true;
        if(string.equals("-")) return true;
        if(string.equals("/")) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Stack<String> stack = new Stack<String>();

        String str, op;
        float num1, num2, result = 0;
        while(scanner.hasNext()){
            str = scanner.nextLine();
            stack.push(str);
            
            if(isOperator(stack.peek())){
                op = stack.pop();

                num2 = Float.parseFloat(stack.pop());
                num1 = Float.parseFloat(stack.pop());

                if(op.equals("+")){
                    result = num1 + num2;
                }
                if(op.equals("*")){
                    result = num1 * num2;
                }
                if(op.equals("-")){
                    result = num1 - num2;
                }
                if(op.equals("/")){
                    result = num1 / num2;
                }

                stack.push(String.valueOf(result));
            }
        }

        System.out.println(stack.pop());

        scanner.close();
    }
}
