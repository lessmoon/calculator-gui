package lessmoon.cal.compiler.inter;

import lessmoon.cal.compiler.lexer.*;

public class Node {
    Node() {}
    void error(String s)throws Exception{
        throw new Exception(s);
    }
}