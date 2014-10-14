package lessmoon.cal.compiler.inter;

import lessmoon.cal.compiler.lexer.*;

public class Expr extends Node {
    public Token op;
    Expr(Token tok){ 
        op = tok;
    }
    public double cal() {
        return 0;
    }
}