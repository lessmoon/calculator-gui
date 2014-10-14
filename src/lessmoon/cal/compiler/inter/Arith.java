package lessmoon.cal.compiler.inter;

import lessmoon.cal.compiler.lexer.*;

public class Arith extends Expr{
    public Expr expr1,expr2;
    public Arith(Token tok,Expr x1,Expr x2){
        super(tok);
        expr1 = x1;
        expr2 = x2;
    }
    
    public double cal() {
        switch(op.tag) {
        case '+':
            return expr1.cal() + expr2.cal();
        case '-':
            return expr1.cal() - expr2.cal();
        case '*':
            return expr1.cal() * expr2.cal();
        case '/':
            return expr1.cal() / expr2.cal();
        default:
            //super.error("Unsupport oprand `" + (char) op.tag + "'");
            return 0;
        }
    }
    
    public String toString(){
        return expr1.toString() + " " + op.toString() + " " + expr2.toString(); 
    }
}