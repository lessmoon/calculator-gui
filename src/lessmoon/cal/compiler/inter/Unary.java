package lessmoon.cal.compiler.inter;

import lessmoon.cal.compiler.lexer.*;

public class Unary extends Expr{
    public Expr expr;
    public Unary(Token tok,Expr e){
        super(tok);
        expr = e;
    }
    
    public double cal() {
        switch(op.tag) {
        case '-':
            return -(expr.cal());
        case '+':
            return expr.cal();
        default:
            //error("Unknown oprand `" + (char)op.tag + "'");
            return 0;
        }
    }

    public String toString() {
        return op.toString() + "(" + expr.toString() + ")"; 
    }
}