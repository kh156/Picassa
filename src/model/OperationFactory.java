package model;

import java.util.List;

public class OperationFactory {
    Operation myOperation;

    public OperationFactory(Operation operation) {
        myOperation = operation;
    }
    
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return myOperation.isThiskindOfOperation(command, operands);
    }
    
    public Operation newOperation(String command, List<Expression> operands) {
        return myOperation.newOperation(command, operands);
    }
}
