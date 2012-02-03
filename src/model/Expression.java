package model;

import java.util.*;


/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public abstract class Expression
{

    /**
     * @return value of expression
     */
    public final RGBColor evaluate (double evalX, double evalY, double currentTime) {
        return evaluateWithVariable(evalX, evalY, currentTime, new HashMap<String, RGBColor>());
    }
    
    public abstract RGBColor evaluateWithVariable(double evalX, double evalY, double currentTime, Map<String, RGBColor> varMap);
    
    /**
     * @return value of expression in case that no parameters passed in
     */
    public final RGBColor evaluate() {
        return evaluate(0, 0, 0);
    }

    /**
     * @return string representation of expression
     */
    public abstract String toString ();
}
