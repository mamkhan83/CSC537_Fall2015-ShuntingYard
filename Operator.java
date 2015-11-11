

/**
 * Operator.java
 */
public interface Operator {

    /***
     *
     * true if the operator is right associative, and
     * false otherwise. By definition, any operator that isn't
     * right associative is left associative.
     */
    boolean isRightAssociative();

    /***
     * Compares the precedence of this operator against another operator.
     *
     * o The operator to compare against.
     *
     *  -1 if this operator is of lower precedence, 1 if it's of greater
     * precedence, and 0 if they're of equal precedence. If two operators are of
     * equal precedence, right associativity and parenthetical groupings must be
     * used to determine precedence.
     */
    int compareOperatorPrecedence(Operator operator);

    /***
     *
     * @return Gets the symbol used to denote the operator.
     */
    char getSign();


}
