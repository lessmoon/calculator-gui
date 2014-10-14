package lessmoon.cal.compiler.parser;

import java.io.*;
import lessmoon.cal.compiler.lexer.*;
import lessmoon.cal.compiler.inter.*;

public class Parser{
    private Lexer lex;
    private Token look;
    public  Parser(Lexer l) throws Exception {lex = l;move();}
    void move()throws Exception{look = lex.scan();}
    void error(String s)throws Exception{throw new Exception(s);}

    void match(int t) throws Exception{
        if(look.tag == t) move(); 
        else error("Dismatched operand `" + look + "' found");
    }

    public void reset() throws Exception {
        lex.reset();
        move();
    }

    public Expr getExpr() throws Exception {
        Expr e = expr();
        if(look.tag != 0)
            error("Wrong operand `" + look + "' found");
        return e;
    }

    public Expr expr()throws Exception{
        Expr x = term();
        while( look.tag == '+'||look.tag == '-'){
            Token tok = look;
            move();
            x = new Arith(tok,x,term());
        }
        return x;
    }

    Expr term()throws Exception{
        Expr x = unary();
        while(look.tag == '*' || look.tag == '/'){
            Token tok = look; move(); x = new Arith(tok,x,unary());
        }
        return x;
    }

    Expr unary()throws Exception{
        Token tmp = look;
        switch(look.tag) {
          case '-'    :case '+'    :
                move();
                return new Unary(tmp,unary());
          default:
                return factor();
        }
    }
    
    Expr factor()throws Exception{
        Expr x = null;
        switch(look.tag){
            case '(':
                move(); 
                x = expr();
                match(')');
                return x;
            case Tag.REAL:
                x = new Constant((Real)look);  
                move();
                return x;
            default:
                error("Syntax error:" + look);
                return x;
        }
    }
}
