package model.expression;

import java.util.List;
import java.util.Map;

import model.Expression;
import model.Operation;
import model.OperationFactory;
import model.OperationProperties;
import model.RGBColor;
import model.util.ColorCombinations;

public class Cosine extends Operation{
    private OperationProperties myProperties = new OperationProperties(new String[]{"cos"}, 1);

    @Override
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return myProperties.checkLegality(command, operands);
    }

    @Override    
    public String toString() {
        return Operation.operationToSring(myCommand, myOperands);
    }

    public Cosine (String command, List<Expression> operands) {
        myCommand = command;
        myOperands = operands;
    }

    private Cosine () {
    }

    public static OperationFactory getFactory() {
        return new OperationFactory(new Cosine());
    }

    @Override
    public Operation newOperation(String command, List<Expression> operands) {
        return new Cosine(command, operands);
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        return ColorCombinations.cos(myOperands.get(0).evaluateWithVariable(evalX, evalY, currentTime, varMap));
    }

}
