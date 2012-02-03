package model;

import java.util.List;


public abstract class Operation extends Expression{
    protected String myCommand;
    protected List<Expression> myOperands;

    public abstract boolean isThiskindOfOperation(String command, List<Expression> operands);

    public abstract Operation newOperation(String command, List<Expression> operands);

    public static String operationToSring(String command, List<Expression> operands) {
        StringBuffer result = new StringBuffer();
        result.append("(");
        result.append(command);
        for (Expression entry:operands) {
            result.append(" " + entry.toString());
        }
        result.append(")");
        return result.toString();
    }
}
