package model.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.Operation;
import model.OperationFactory;
import model.OperationProperties;
import model.RGBColor;

public class Let extends Operation{
    private OperationProperties myProperties = new OperationProperties(new String[]{"let"}, 3);
    
    @Override
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return (myProperties.checkLegality(command, operands) 
                    && operands.get(0).getClass().isInstance(new Variable("")));
    }
    
    @Override    
    public String toString() {
        return Operation.operationToSring(myCommand, myOperands);
    }

    public Let (String command, List<Expression> operands) {
        myCommand = command;
        myOperands = operands;
    }
    
    private Let () {
    }
    
    public static OperationFactory getFactory() {
        return new OperationFactory(new Let());
    }

    @Override
    public Operation newOperation(String command, List<Expression> operands) {
        return new Let(command, operands);
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        Map<String, RGBColor> newMap = new HashMap<String, RGBColor>(varMap);  
        newMap.put(myOperands.get(0).toString(), myOperands.get(1).evaluateWithVariable(evalX, evalY, currentTime, varMap));
        return myOperands.get(2).evaluateWithVariable(evalX, evalY, currentTime, newMap);
    }

}
