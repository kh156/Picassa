package model.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Expression;
import model.Operation;
import model.OperationFactory;
import model.OperationProperties;
import model.RGBColor;
import model.util.ColorCombinations;

public class Sum extends Operation{
    private OperationProperties myProperties = new OperationProperties(new String[]{"sum"}, -1);

    @Override
    public boolean isThiskindOfOperation(String command, List<Expression> operands) {
        return myProperties.checkLegality(command, operands);
    }

    @Override    
    public String toString() {
        return Operation.operationToSring(myCommand, myOperands);
    }

    public Sum (String command, List<Expression> operands) {
        myCommand = command;
        myOperands = operands;
    }

    private Sum () {
    }

    public static OperationFactory getFactory() {
        return new OperationFactory(new Sum());
    }

    @Override
    public Operation newOperation(String command, List<Expression> operands) {
        return new Sum(command, operands);
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        List<RGBColor> colorList = new ArrayList<RGBColor>(); 
        for (Expression entry:myOperands)
            colorList.add(entry.evaluateWithVariable(evalX, evalY, currentTime, varMap));
        return ColorCombinations.sum(colorList);
    }

}
