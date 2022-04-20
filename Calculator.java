import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Calculator {
    static boolean isOperator(String string){
        if(string.equals("+")) return true;
        if(string.equals("*")) return true;
        if(string.equals("-")) return true;
        if(string.equals("/")) return true;
        return false;
    }

    static boolean isNumber(String number){
        for(int i = 0 ; i < number.length() ; i++){
            if(!Character.isDigit(number.charAt(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try(BufferedReader br = Files.newBufferedReader(Paths.get("Calc1.stk"))){
            String str;

            Queue<Token> input = new LinkedList<Token>();

            while((str = br.readLine()) != null){
                if(isOperator(str)){
                    if(str.equals("+")) input.add(new Token(TokenType.PLUS, "+"));
                    else if(str.equals("*")) input.add(new Token(TokenType.STAR, "*"));
                    else if(str.equals("-")) input.add(new Token(TokenType.MINUS, "-"));
                    else if(str.equals("/")) input.add(new Token(TokenType.SLASH, "/"));
                }else if(isNumber(str)){
                    input.add(new Token(TokenType.NUM, str));
                }else{
                    throw new InvalidInputException(str);
                }
            }

            Stack<String> stack = new Stack<String>();

            while(!input.isEmpty()){
                Token token = input.remove();

                if(token.type == TokenType.NUM){
                    stack.add(token.lexeme);
                }else{
                    float num1, num2, result = 0;

                    num2 = Float.parseFloat(stack.pop());
                    num1 = Float.parseFloat(stack.pop());

                    if(token.type == TokenType.PLUS){
                        result = num1 + num2;
                    }else if(token.type == TokenType.MINUS){
                        result = num1 - num2;
                    }else if(token.type == TokenType.STAR){
                        result = num1 * num2;
                    }else if(token.type == TokenType.SLASH){
                        result = num1 / num2;
                    }

                    stack.push(String.valueOf(result));
                }
            }
            
            System.out.println(stack.pop());
        }catch(IOException e){
            System.err.format("IOException: %s%n", e);
        }catch(InvalidInputException e){
            System.err.format("Unexpected character:: %s%n", e);
        }

        scanner.close();
    }
}
