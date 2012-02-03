package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.Operation;
import model.OperationFactory;
import model.OperationProperties;
import model.RGBColor;
import model.util.ColorCombinations;

public class Clamp extends Operation{
    private OperationProperties myProperties = new OperationProperties(new String[]{"clamp"}, 1);

    @Override
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return myProperties.checkLegality(command, operands);
    }

    @Override    
    public String toString() {
        return Operation.operationToSring(myCommand, myOperands);
    }

    public Clamp (String command, List<Expression> operands) {
        myCommand = command;
        myOperands = operands;
    }

    private Clamp () {
    }

    public static OperationFactory getFactory() {
        return new OperationFactory(new Clamp());
    }

    @Override
    public Operation newOperation(String command, List<Expression> operands) {
        return new Clamp(command, operands);
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        return ColorCombinations.clamp(myOperands.get(0).evaluateWithVariable(evalX, evalY, currentTime, varMap));
    }

}
