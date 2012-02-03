package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.Operation;
import model.OperationFactory;
import model.OperationProperties;
import model.RGBColor;
import model.util.ColorCombinations;

public class RandomExp extends Operation{
    private OperationProperties myProperties = new OperationProperties(new String[]{"random"}, 0);
    
    @Override
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return myProperties.checkLegality(command, operands);
    }
    
    @Override    
    public String toString() {
        return Operation.operationToSring(myCommand, myOperands);
    }

    public RandomExp (String command, List<Expression> operands) {
        myCommand = command;
        myOperands = operands;
    }
        
    private RandomExp () {
    }
    
    public static OperationFactory getFactory() {
        return new OperationFactory(new RandomExp());
    }

    @Override
    public Operation newOperation(String command, List<Expression> operands) {
        return new RandomExp(command, operands);
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        return ColorCombinations.random();
    }

}
