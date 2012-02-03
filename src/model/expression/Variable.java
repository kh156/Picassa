package model.expression;

import java.util.Map;

import model.Expression;
import model.ParserException;
import model.RGBColor;

public class Variable extends Expression{
    
    private String myName;
    
    public Variable(String name) {
        myName = name;
    }
    
    public String variableName() {
        return myName;
    }
            
    @Override
    public String toString() {
        return myName;
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        if (varMap.containsKey(myName))
            return varMap.get(myName);
        if (myName.equals("x"))
            return new RGBColor(evalX);
        if (myName.equals("y"))
            return new RGBColor(evalY);
        if (myName.equals("t"))
            return new RGBColor(currentTime);
        throw new ParserException("Undefined Variable: " + myName);
    }

}
