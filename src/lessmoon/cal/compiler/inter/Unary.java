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
        case Tag.SQR:
            return Math.sqrt(expr.cal());
        case Tag.TAN:
            return Math.tan(expr.cal());
        case Tag.SIN:
            return Math.sin(expr.cal());
        case Tag.COS:
            return Math.cos(expr.cal());
        case Tag.EXP:
            return Math.exp(expr.cal());
        case Tag.LOG:
            return Math.log10(expr.cal());
        case Tag.LN:
            return Math.log(expr.cal());
        default:
            //error("Unknown oprand `" + (char)op.tag + "'");
            return 0;
        }
    }

    public String toString() {
        return op.toString() + "(" + expr.toString() + ")"; 
    }
}