
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * A simple implementation of the Shunting Yard algorithm to parse an
 * infix expression and generate either an abstract syntax tree or another
 * expression in Reverse Polish notation. This class duplicates code between
 * and as the underlying algorithm is
 * nearly identical. You can easily yield RPN given an Node via post-order
 * traversal of the tree.
 * Error handling is almost non existent, all operators are taken as single
 * characters. Both of these issues are easily corrected for.
 */
public class ShuntingYardParser {

    private final Map<Character, Operator> operators;

    private static void addNode(Stack<Node> stack, char operator) {
        final Node rightASTNode = stack.pop();
        final Node leftASTNode = stack.pop();
        stack.push(new Node(operator, leftASTNode, rightASTNode));
    }

    /***
     * Creates a new ShuntingYardParser for the given operators.
     * operators A collection of operators that should be recognized by
     * the parser.
     */
    public ShuntingYardParser(Collection<Operator> operators) {
        this.operators = new HashMap<>();
        for(Operator o : operators) {
            this.operators.put(o.getSign(), o);
        }
    }

    /***
     * Convert an expression in infix notation to an abstract syntax tree.
     * input The expression, in infix notation.
     * return An Node that serves as the root of the Node.
     */
    public Node convertInfixNotationToAST(final String input) {
        final Stack<Character> operatorStack = new Stack<>();
        final Stack<Node> operandStack = new Stack<>();
        final char[] chars = input.toCharArray();
        main:
        for(char c : chars) {
            char popped;
            switch(c) {
                case ' ':
                    break;
                case '(':
                    operatorStack.push('(');
                    break;
                case ')':
                    while(!operatorStack.isEmpty()) {
                        popped = operatorStack.pop();
                        if('(' == popped) {
                            continue main;
                        } else {
                            addNode(operandStack, popped);
                        }
                    }
                    throw new IllegalStateException("Unbalanced right " +
                            "parentheses");
                default:
                    if(operators.containsKey(c)) {
                        final Operator o1 = operators.get(c);
                        Operator o2;
                        while(!operatorStack.isEmpty() && null != (o2 =
                                operators.get(operatorStack.peek()))) {
                            if((!o1.isRightAssociative() &&
                                    0 == o1.compareOperatorPrecedence(o2)) ||
                                    o1.compareOperatorPrecedence(o2) < 0) {
                                operatorStack.pop();
                                addNode(operandStack, o2.getSign());
                            } else {
                                break;
                            }
                        }
                        operatorStack.push(c);
                    } else {
                        operandStack.push(new Node(c, null, null));
                    }
                    break;
            }
        }
        while(!operatorStack.isEmpty()) {
            addNode(operandStack, operatorStack.pop());
        }
        return operandStack.pop();
    }

    /***
     * Convert an expression in infix notation to an expression in Reverse
     * Polish Notation.
     * input The expression, in infix notation. 
     * An expression in Reverse Polish notation.
     */
    public String convertInfixNotationToRPN(final String input) {
        final Stack<Character> operatorStack = new Stack<>();
        final StringBuilder sb = new StringBuilder();
        final char[] chars = input.toCharArray();
        main:
        for(char c : chars) {
            char popped;
            switch(c) {
                case ' ':
                    break;
                case '(':
                    operatorStack.push('(');
                    break;
                case ')':
                    while(!operatorStack.isEmpty()) {
                        popped = operatorStack.pop();
                        if('(' == popped) {
                            continue main;
                        } else {
                            sb.append(" ").append(popped);
                        }
                    }
                    throw new IllegalStateException("Unbalanced right " +
                            "parentheses");
                default:
                    if(operators.containsKey(c)) {
                        final Operator o1 = operators.get(c);
                        Operator o2;
                        while(!operatorStack.isEmpty() && null != (o2 =
                                operators.get(operatorStack.peek()))) {
                            if((!o1.isRightAssociative() &&
                                    0 == o1.compareOperatorPrecedence(o2)) ||
                                    o1.compareOperatorPrecedence(o2) < 0) {
                                operatorStack.pop();
                                sb.append(" ").append(o2.getSign());
                            } else {
                                break;
                            }
                        }
                        operatorStack.push(c);
                    } else {
                        if(sb.length() > 0) {
                            sb.append(" ");
                        }
                        sb.append(c);
                    }
                    break;
            }
        }
        while(!operatorStack.isEmpty()) {
            sb.append(" ").append(operatorStack.pop());
        }
        return sb.toString();
    }


}
