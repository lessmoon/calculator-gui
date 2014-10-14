package lessmoon.cal.compiler.lexer;

public class Word extends  Token {
    public String lexeme = "";
    public Word(String s, int tag){super(tag);lexeme = s;}
    public String toString(){return lexeme;}
}