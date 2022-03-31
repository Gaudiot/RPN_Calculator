import java.util.Scanner;
import java.util.Stack;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


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

        try(BufferedReader br = Files.newBufferedReader(Paths.get("Calc1.stk"))){
            String str, op;
            float num1, num2, result = 0;
            while((str = br.readLine()) != null){
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
        }catch (IOException e){
            System.err.format("IOException: %s%n", e);
        }

        System.out.println(stack.pop());

        scanner.close();
    }
}
