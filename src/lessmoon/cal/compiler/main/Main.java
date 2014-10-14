package lessmoon.cal.compiler.main;

import java.io.*;
import lessmoon.cal.compiler.lexer.*; 
import lessmoon.cal.compiler.parser.*;
import lessmoon.cal.compiler.inter.*;

public class Main {
    public static void main(String[] args)throws Exception{
        Lexer lex = new Lexer();
        Parser parser ;
        try {
            parser = new Parser(lex);
            Expr e =  parser.expr();
            System.out.println(e);
            System.out.println(e.cal());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } 
        return ;
    }
}
