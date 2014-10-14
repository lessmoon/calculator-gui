package lessmoon.cal.compiler.inter;

import lessmoon.cal.compiler.lexer.*;

public class Constant extends Expr{
    public Constant(Real r){
        super(r);
    }
    public double cal() {
        return ((Real)op).value;
    }
    
    public String toString() {
        return op.toString();
    }
}