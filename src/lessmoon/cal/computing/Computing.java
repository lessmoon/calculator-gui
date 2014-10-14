package lessmoon.cal.computing;

import lessmoon.cal.compiler.lexer.*;
import lessmoon.cal.compiler.parser.*;

class StringInputStream implements LexerInputStream {
    String str;
    int pIndex;
    StringInputStream(){
        str = null;
        pIndex = 0;
    }
    
    public char getch() throws Exception {
        if( pIndex >= str.length() ) {
            return 0;
        }
        return str.charAt(pIndex++);
    }
    
    public void feed(String s) {
        pIndex = 0;
        str = s;
    }
}

public class Computing {
    Lexer lex;
    Parser p;
    StringInputStream sis;
    
    public Computing() {
        sis = new StringInputStream();
        sis.feed("");
        lex = new Lexer(sis);
        try {
            p   = new Parser(lex);
        } catch (Exception e) {
            System.err.println("Bad things happened:" + e.getMessage() + ".Abort!");
            System.exit(1);
        }
    }

    public double doCalculate(String strToCompute) throws Exception {
        sis.feed(strToCompute);
        p.reset();
        return p.getExpr().cal();
    }
}