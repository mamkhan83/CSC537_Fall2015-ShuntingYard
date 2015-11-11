


/**
 * BaseOPerator.java
 */
public class BaseOperator implements Operator {

    private final char sign;
    private final boolean rightAssociative;
    private final int operatorPrecedence;

    /***
     * Creates a new BaseOperator.
     *
     * sign The sign used to represent the operator.
     * rightAssociative true if the operator is right
     * associative, and false otherwise.
     * operatorPrecedence A numerical operatorPrecedence for the operator relative to
     * all other operators in use.
     */
    public BaseOperator(char sign, boolean rightAssociative,
                        int operatorPrecedence) {
        this.sign = sign;
        this.rightAssociative = rightAssociative;
        this.operatorPrecedence = operatorPrecedence;
    }

    @Override
    public boolean isRightAssociative() {
        return rightAssociative;
    }

    @Override
    public int compareOperatorPrecedence(Operator operator) {
        if(operator instanceof BaseOperator) {
            BaseOperator other = (BaseOperator) operator;
            return operatorPrecedence > other.operatorPrecedence ? 1 :
                    other.operatorPrecedence == operatorPrecedence ? 0 : -1;
        } else {
            // Defer the comparison to the second operator reflectively
            return -operator.compareOperatorPrecedence(this);
        }
    }

    @Override
    public char getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return Character.toString(sign);
    }


}
