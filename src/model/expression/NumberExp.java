package model.expression;

import java.util.Map;

import model.Expression;
import model.RGBColor;


public class NumberExp extends Expression{
    private double myValue;


    /**
     * Create expression representing the given constant value
     */
    public NumberExp (double value) {
        myValue = value;
    }
    
    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(myValue);
        return result.toString();
    }

    @Override
    public RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap) {
        return new RGBColor(myValue);
    }

}
