package lessmoon.cal.compiler.lexer;

import java.io.*; import java.util.*;

public class Lexer {
    char peek = ' ';
    LexerInputStream is;
    
    public Lexer(){
        is = new LexerInputStream() {
            public char getch() throws Exception{
                return (char)System.in.read();
            }
        };
    }

    public Lexer(LexerInputStream is) {
        this.is = is;
    }

    void readch() throws Exception{
        peek = is.getch();
    }

    boolean readch(char c)throws Exception{
        readch();
        if(peek != c) return false;
        peek = ' ';
        return true;
    }
    
    public void reset() {
        peek = ' ';
    }

    public Token scan()throws Exception{
        for(;;readch()){
            if(peek == ' ' || peek == '\t') continue;
            else if(peek == 13) {readch();}
            else {
                break;
            }
        }
        if(Character.isDigit(peek)){
            int v = 0;
            do{
                v = 10*v + Character.digit(peek,10);readch();
            }while(Character.isDigit(peek));
            if(peek != '.') return new Real(v);
            double x = v; double d = 10;
            for(;;){
                readch();
                if(! Character.isDigit(peek))break;
                x = x + Character.digit(peek,10)/d;d = d*10;
            }
            return new Real(x);
        }
        if(Character.isLetter(peek)){
            StringBuffer b = new StringBuffer();
            do{
                b.append(peek);readch();
            } while(Character.isLetterOrDigit(peek));
            String s = b.toString();
            return new Word(s,Tag.UNKNOWN);
        }
 
        Token tok = new Token(peek);peek = ' ';
        return tok;
    }
}