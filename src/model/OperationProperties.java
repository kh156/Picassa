package model;

import java.util.List;

public class OperationProperties {
    private String[] myLegalCommandnames;
    private int myLegalOperandnumber; 

    public OperationProperties(String[] legalCommandnames, int legalOperandnumber) {
        this.myLegalCommandnames = legalCommandnames;
        this.myLegalOperandnumber = legalOperandnumber;
    }

    public boolean checkLegality(String command, List<Expression> operands) {
        boolean flag = false;
        for (String name: myLegalCommandnames) {
            if (name.equals(command)) {
                flag = true;
                break;
            }
        }
        if (!flag) return false;
        if (myLegalOperandnumber == -1)
            return operands.size() >= 1;
        if (operands.size() != myLegalOperandnumber) {
            throw new ParserException("Illegal Number Of Operands: " + myLegalOperandnumber + " operands expected");
        }
        else {
            return true;
        }

    }
}
