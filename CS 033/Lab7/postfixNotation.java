// import java.util.Scanner;

/**
 * This class runs and tests a postfix ("Reverse Polish Notation") expression evaluator.
 * @author Jordan Marcelo
 * 04/01/25
 * CS 033
 * Professor Ashraf
 */
public class postfixNotation {
    /**
     * This main method loops through test cases to ensure the postfix evaluation
     * method works correctly.
     * 
     * @param args The instructions for the program to run
     */
    public static void main(String[] args) {
        // declare test case expressions and correct/expected answers
        String[] expressions = {"5 1 2 + 4 * + 3 -",
                                "3 4 + 2 *",
                                "15 7 1 1 + - / 3 * 2 1 1 + + -",
                                "4 + 3",
                                "2 3 * -",
                                "10 5 m"};
        String[] expected = {"14.0",
                            "14.0",
                            "5.0",
                            "Insufficient operands",
                            "Insufficient operands",
                            "Illegal expression part: m"};
        // loop through test cases and compare to expected answers, tallying results
        int numCorrect = 0;
        for (int i = 0; i < expressions.length; i++) {
            // result becomes either the evaluation of the postfix expression
            // or the error message resulting from trying to make it so
            String result;
            try {
                result = Double.toString(evaluatePostfix(expressions[i]));
            }
            catch (Exception e) {
                result = e.getMessage();
            }
            // evaluation is correct if numbers or errors are as expected
            boolean correct = result.equals(expected[i]);
            // increment numCorrect if evaluation is correct
            numCorrect += correct ? 1 : 0;
            // summarize results
            System.out.println("Evaluating the expression:\n\t" + expressions[i]
            + "\nResult:\n\t" + result
            + "\nExpected:\n\t" + expected[i]
            + "\nExpected results match: " + correct
            + "\n------------------------------------------");
        }
        // summarize accuracy
        System.out.println("Trials correct: " + String.format("%.2f", 100.0 * numCorrect / expressions.length) + "%");

        // an optional input-based evaluation using java.util.Scanner:
        /*
        System.out.println("Enter a postfix (RPN) expression to be evaluated:");
        String input;
        try (Scanner kb = new Scanner(System.in)) {
            input = kb.nextLine();
            kb.close();
            System.out.println(evaluatePostfix(input));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */
    }
    /**
     * This method takes an expression in string form and evaluates each term
     * to be an operator or operand, calculating the final result of the evaluation
     * along the way.
     * 
     * @param expression String form of a postfix expression
     * @return The evaluation of the postfix expression
     * @throws IllegalArgumentException if mismatched operands and operators, or expression contains illegal character
     */
    public static double evaluatePostfix(String expression) {
        // instantiate a stack for operands
        Stack<Double> parts = new Stack<>();
        // loop through non-space phrases in expression
        for (String i : expression.split(" ")) {
            try {
                // push operand to stack if it can be cast as a double
                parts.push(Double.valueOf(i));
            } catch (NumberFormatException e) {
                // otherwise assume i is an operator
                // prepare for arithmetic operations on operands
                double operand2 = 0;
                double operand1 = 0;
                // ensure there are sufficient operands
                // (each operator has 2 operands)
                try {
                    // pop operands in reverse order since stack is LIFO
                    operand2 = parts.pop();
                    operand1 = parts.pop();
                } catch (Exception s) {
                    // throw exception if insufficient operators for operands
                    throw new IllegalArgumentException("Insufficient operands");
                }
                // act on operands according to i, pushing result
                switch (i) {
                    case "+" -> parts.push(operand1 + operand2);
                    case "-" -> parts.push(operand1 - operand2);
                    case "*" -> parts.push(operand1 * operand2);
                    case "/" -> parts.push(operand1 / operand2);
                    // throw illegal arg exception if i is not an operator
                    default -> throw new IllegalArgumentException("Illegal expression part: " + i);
                }
            }
        }
        // throw illegal argument exception if multiple operands leftover
        if (parts.size() > 1)
            throw new IllegalArgumentException("Insufficient operators");
        // otherwise last element is result of proper evaluation
        return parts.pop();
    }
}