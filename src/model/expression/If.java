package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.Operation;
import model.OperationFactory;
import model.OperationProperties;
import model.RGBColor;

public class If extends Operation{
    private OperationProperties myProperties = new OperationProperties(new String[]{"if"}, 3);
    
    @Override
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return (myProperties.checkLegality(command, operands)); 
    }
    
    @Override    
    public String toString() {
        return Operation.operationToSring(myCommand, myOperands);
    }

    public If (String command, List<Expression> operands) {
        myCommand = command;
        myOperands = operands;
    }
    
    private If () {
    }
    
    public static OperationFactory getFactory() {
        return new OperationFactory(new If());
    }

    @Override
    public Operation newOperation(String command, List<Expression> operands) {
        return new If(command, operands);
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        RGBColor c = myOperands.get(0).evaluateWithVariable(evalX, evalY, currentTime, varMap);
        double toExam = (c.getRed() + c.getGreen() + c.getBlue()) / 3.0;
        return (toExam > 0) ? myOperands.get(1).evaluateWithVariable(evalX, evalY, currentTime, varMap) : myOperands.get(2).evaluateWithVariable(evalX, evalY, currentTime, varMap);
    }

}
