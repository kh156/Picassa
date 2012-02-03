package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.Operation;
import model.OperationFactory;
import model.OperationProperties;
import model.RGBColor;
import model.util.ColorCombinations;

public class ColorExp extends Operation{
private OperationProperties myProperties = new OperationProperties(new String[]{"color"}, 3);
    
    @Override
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return myProperties.checkLegality(command, operands);
    }
    
    @Override    
    public String toString() {
        return Operation.operationToSring(myCommand, myOperands);
    }
    
    public ColorExp (String command, List<Expression> operands){
        myCommand = command;
        myOperands = operands;
    }
    
    private ColorExp () {
    }
    
    public static OperationFactory getFactory() {
        return new OperationFactory(new ColorExp());
    }

    @Override
    public Operation newOperation(String command, List<Expression> operands) {
        return new ColorExp(command, operands);
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        return ColorCombinations.color(myOperands.get(0).evaluateWithVariable(evalX, evalY, currentTime, varMap), myOperands.get(1).evaluateWithVariable(evalX, evalY, currentTime, varMap), myOperands.get(2).evaluateWithVariable(evalX, evalY, currentTime, varMap));
    }
}
