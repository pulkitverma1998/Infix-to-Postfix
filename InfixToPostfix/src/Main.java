// Pulkit Verma
// Class (CECS 274-05)
// Project Name (Program 5 - RPN Equation)
// Due Date (Nov 26, 2018)

import java.util.Scanner;

public class Main {

    static String[] token;

    // This method checks if a token is an operator or an operand
    public static boolean isOperator(String operator) {
        if (operator.equals("*") || operator.equals("^") || operator.equals("/") || operator.equals("-") || operator.equals("+") || operator.equals("(") || operator.equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    // This method returns the precedence of an operator
    public static int precedenceOfOperator(String operator) {
        if (operator.equals("(")) {
            return 0;
        } else if (operator.equals("^")) {
            return 3;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else {
            return 1;
        }
    }

    // This method converts an infix equation to a postfix equation
    public static Queue infixToPostfix(String[] infix) {
        Queue queue = new Queue();
        Stack operatorStack = new Stack();
        boolean floor = true;
        int j = 0;

        for (int i = 0; i < infix.length; i++) {
            String token = infix[i];
            if (isOperator(token) == false) {
                queue.addToQueue(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
                floor = true;
            } else if (token.equals(")")) {
                String operator = operatorStack.pop();
                queue.addToQueue(operator);
                operatorStack.pop();
            } else if (infix[0].equals("") && j == 0) {
                operatorStack.push(token);
                queue.removeFromQueue();
                queue.addToQueue("0");
                j = 1;
                floor = false;
            } else {
                if (operatorStack.size() == 0 || floor) {
                    operatorStack.push(token);
                    floor = false;
                } else if (precedenceOfOperator(token) > precedenceOfOperator(operatorStack.getTopItem()) && token != ")") {
                    operatorStack.push(token);
                } else {
                    while (precedenceOfOperator(token) <= precedenceOfOperator(operatorStack.getTopItem())) {
                        String operator = operatorStack.pop();
                        queue.addToQueue(operator);
                        if (operatorStack.size() == 0) {
                            break;
                        }
                    }
                    operatorStack.push(token);
                }
            }
        }
        while (operatorStack.size() != 0) {
            String operator = operatorStack.pop();
            queue.addToQueue(operator);
        }
        return queue;
    }

    // This method solves a postfix equation
    public static double solveRPN(Queue rpnEquation) {
        String operand = "0";
        double result = 0;
        double operand1;
        double operand2;
        Stack operandStack = new Stack();
        Queue postfix = rpnEquation;

        while (postfix.size() != 0) {

            operand = postfix.removeFromQueue();

            if (isOperator(operand) == false) {
                operandStack.push(operand);
            } else {
                operand1 = Double.valueOf(operandStack.pop());
                operand2 = Double.valueOf(operandStack.pop());
                switch (operand) {
                    case "*":
                        result = operand2 * operand1;
                        operandStack.push(String.valueOf(result));
                        break;
                    case "+":
                        result = operand2 + operand1;
                        operandStack.push(String.valueOf(result));
                        break;
                    case "-":
                        result = operand2 - operand1;
                        operandStack.push(String.valueOf(result));
                        break;
                    case "/":
                        result = operand2 / operand1;
                        operandStack.push(String.valueOf(result));
                        break;
                    case "^":
                        result = Math.pow(operand2, operand1);
                        operandStack.push(String.valueOf(result));
                        break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String expr;
        System.out.print("Enter an equation: ");
        expr = in.nextLine();

        while (expr.equals("exit") == false) {
            String exprCopy = expr;

            // Use replaceAll to force spaces in equation
            exprCopy = exprCopy.replaceAll("\\+", " + ");
            exprCopy = exprCopy.replaceAll("\\-", " - ");
            exprCopy = exprCopy.replaceAll("\\*", " * ");
            exprCopy = exprCopy.replaceAll("\\/", " / ");
            exprCopy = exprCopy.replaceAll("\\(", " ( ");
            exprCopy = exprCopy.replaceAll("\\)", " ) ");
            exprCopy = exprCopy.replaceAll("\\^", " ^ ");

            // Use split to tokenize equation
            String delims = "[ ]+"; // one or more spaces

            token = exprCopy.split(delims);

            // Solving postfix equation
            Queue postfix = infixToPostfix(token);
            System.out.print("RPN: ");
            postfix.displayQueue();

            System.out.printf("\nAnswer: %.2f\n", solveRPN(postfix));
            System.out.print("Enter an equation: ");
            expr = in.nextLine();
        }
    }
}
