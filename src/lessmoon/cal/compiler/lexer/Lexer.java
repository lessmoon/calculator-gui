package lessmoon.cal.compiler.lexer;

import java.io.*; import java.util.*;

public class Lexer {
    char peek = ' ';
    Hashtable words = new Hashtable();
    LexerInputStream is;
    
    void reserve(Word w){words.put(w.lexeme,w);}
    public Lexer(){
        is = new LexerInputStream() {
            public char getch() throws Exception{
                return (char)System.in.read();
            }
        };
        initKeepWords();
    }

    public Lexer(LexerInputStream is) {
        this.is = is;
        initKeepWords();
    }

    void initKeepWords() {
        reserve(new Word("sqr",Tag.SQR));
        reserve(new Word("tan",Tag.TAN));
        reserve(new Word("sin",Tag.SIN));
        reserve(new Word("cos",Tag.COS));
        reserve(new Word("exp",Tag.EXP));
        reserve(new Word("log",Tag.LOG));
        reserve(new Word("ln",Tag.LN));
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
            Word w = (Word)words.get(s);
            if(w != null) return w;
            return new Word(s,Tag.UNKNOWN);
        }
 
        Token tok = new Token(peek);peek = ' ';
        return tok;
    }
}