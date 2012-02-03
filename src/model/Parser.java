package model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ParserException.Type;
import model.expression.*;



/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser 
 * is used 
 *   http://en.wikipedia.org/wiki/Recursive_descent_parser
 *   
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser
{
    // double is made up of an optional negative sign, followed by a sequence 
    // of one or more digits, optionally followed by a period, then possibly 
    // another sequence of digits
    private static final Pattern DOUBLE_REGEX =
        Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");
    // expression begins with a left paren followed by the command name, 
    // which is a sequence of alphabetic characters
    private static final Pattern OPERATION_BEGIN_REGEX =
            Pattern.compile("\\(([!-'*-~]+)");
        //Pattern.compile("\\(([a-zA-Z]+)");
    // a variable is a sequence of character 
    private static final Pattern COORDINATE_REGEX =
        Pattern.compile("[a-zA-Z]+");


    // different possible kinds of expressions
    private static enum ExpressionType
    {
        NUMBER, OPERATION, COORDINATE
    }


    // state of the parser
    private int myCurrentPosition;
    private String myInput;


    /**
     * Converts given string into expression tree.
     * 
     * @param input expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    
    public Expression makeExpression (String input)
    {
        myInput = input;
        myCurrentPosition = 0;
        Expression result = parseExpression();
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                      myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }


    private ExpressionType getExpressionType ()
    {
        skipWhiteSpace();
        if (isNumber())          return ExpressionType.NUMBER;
        if (isOperation())       return ExpressionType.OPERATION;
        if (isCoordinate())      return ExpressionType.COORDINATE;
        else                     throw new ParserException("Unexpected Character " + currentCharacter());
    }


    private Expression parseExpression ()
    {
        switch (getExpressionType())
        {
            case NUMBER:
                return parseNumber();
            case OPERATION:
                return parseOperation();
            case COORDINATE:
                return parseCoordinate();
            default:
                throw new ParserException("Unexpected expression type " +
                                          getExpressionType().toString());
        }
    }


    private boolean isNumber ()
    {
        Matcher doubleMatcher =
            DOUBLE_REGEX.matcher(myInput.substring(myCurrentPosition));
        return doubleMatcher.lookingAt();
    }


    private boolean isOperation ()
    {
        Matcher operationMatcher =
            OPERATION_BEGIN_REGEX.matcher(myInput.substring(myCurrentPosition));
        return operationMatcher.lookingAt();
    }
    
    private boolean isCoordinate ()
    {
        Matcher coordinateMatcher =
                COORDINATE_REGEX.matcher(myInput.substring(myCurrentPosition));
        return coordinateMatcher.lookingAt();
    }

    
    private Expression parseCoordinate ()
    {
        Matcher coordinateMatcher = COORDINATE_REGEX.matcher(myInput);
        coordinateMatcher.find(myCurrentPosition);
        String name =
            myInput.substring(coordinateMatcher.start(), coordinateMatcher.end());
        myCurrentPosition = coordinateMatcher.end();
        
        return new Variable(name);
    }

    private Expression parseNumber ()
    {
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(myInput);
        doubleMatcher.find(myCurrentPosition);
        String numberMatch =
            myInput.substring(doubleMatcher.start(), doubleMatcher.end());
        myCurrentPosition = doubleMatcher.end();
        
        double value = Double.parseDouble(numberMatch);
        return new NumberExp(value);
    }


    private Expression parseOperation ()
    {
        Matcher operationMatcher = OPERATION_BEGIN_REGEX.matcher(myInput);
        operationMatcher.find(myCurrentPosition);
        String commandName = operationMatcher.group(1);
        myCurrentPosition = operationMatcher.end();
        
        ArrayList<Expression> operandList = new ArrayList<Expression>();
        
        skipWhiteSpace();
        while (currentCharacter() != ')') {
            Expression newOperand = parseExpression();
            operandList.add(newOperand);
            skipWhiteSpace();
        }
                
        List<OperationFactory> kindsOfOperations = new ArrayList<OperationFactory>();
        kindsOfOperations.add(Plus.getFactory());
        kindsOfOperations.add(Minus.getFactory());
        kindsOfOperations.add(Multiply.getFactory());
        kindsOfOperations.add(Divide.getFactory());
        kindsOfOperations.add(Modulus.getFactory());
        kindsOfOperations.add(Exponent.getFactory());
        kindsOfOperations.add(Negate.getFactory());
        kindsOfOperations.add(ColorExp.getFactory());
        kindsOfOperations.add(Let.getFactory());
        kindsOfOperations.add(RandomExp.getFactory());
        kindsOfOperations.add(Floor.getFactory());
        kindsOfOperations.add(Ceil.getFactory());
        kindsOfOperations.add(Absolute.getFactory());
        kindsOfOperations.add(Clamp.getFactory());
        kindsOfOperations.add(Wrap.getFactory());
        kindsOfOperations.add(Sine.getFactory());
        kindsOfOperations.add(Cosine.getFactory());
        kindsOfOperations.add(Tangent.getFactory());
        kindsOfOperations.add(Arctangent.getFactory());
        kindsOfOperations.add(Logarithm.getFactory());
        kindsOfOperations.add(RgbToYCrCb.getFactory());
        kindsOfOperations.add(YCrCbtoRGB.getFactory());
        kindsOfOperations.add(PerlinColor.getFactory());
        kindsOfOperations.add(PerlinBW.getFactory());
        kindsOfOperations.add(Sum.getFactory());
        kindsOfOperations.add(Product.getFactory());
        kindsOfOperations.add(Average.getFactory());
        kindsOfOperations.add(Min.getFactory());
        kindsOfOperations.add(Max.getFactory());
        kindsOfOperations.add(If.getFactory());


        skipWhiteSpace();
        if (currentCharacter() == ')')
        {
            myCurrentPosition++;
            for (OperationFactory entry: kindsOfOperations) {
                if (entry.isThiskindOfOperation(commandName, operandList)) {
                    return entry.newOperation(commandName, operandList);
                }
            }
            throw new ParserException("Unknown Command " + commandName, Type.UNKNOWN_COMMAND);
        }
        else
        {
            throw new ParserException("Expected close paren, instead found " +
                                      myInput.substring(myCurrentPosition));
        }
    }

    private void skipWhiteSpace ()
    {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }

    private char currentCharacter ()
    {
        return myInput.charAt(myCurrentPosition);
    }

    private boolean notAtEndOfString ()
    {
        return myCurrentPosition < myInput.length();
    }
}
