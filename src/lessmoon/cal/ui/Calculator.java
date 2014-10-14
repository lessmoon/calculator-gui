package lessmoon.cal.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import lessmoon.cal.computing.*;

public class Calculator extends JFrame {
    
    JTextField txt = new JTextField(25);
    ButtonListener bl = new ButtonListener();
    Computing com  = new Computing();
    
    double  lastAnswer          = 0;
    boolean PresentTextIsValid  = true;
    
    public Calculator() {
        setLayout(new FlowLayout());
        txt.setEditable(false);
        add(txt);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(6,5,1,1));
        add(jp);
        String[] ButtonNames = {    "Del","CE" ,"ans" ,"sin" ,"cos" ,
                                    "ln","log","sqr" ,"tan" ,"exp" ,
                                    "7" ,"8"  ,"9"   ,"^"   ,"1/x" ,
                                    "4" ,"5"  ,"6"   ,"+"   ,"*"   ,
                                    "1" ,"2"  ,"3"   ,"-"   ,"/"   ,
                                    "0" ,"."  ,"("   ,")"   ,"="
                                };
        for(String name : ButtonNames) {
            JButton b = new JButton(name);
            b.addActionListener(bl);
            jp.add(b);
        }
    }
    
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(!PresentTextIsValid) {
                txt.setText("");
                PresentTextIsValid = true;
            }

            String name = ((JButton)e.getSource()).getText();
            switch( name ) {
            case "Del" : 
                if(!txt.getText().isEmpty())
                    txt.setText(txt.getText().substring(0,txt.getText().length() - 1));
                return;
            case "CE" :
                txt.setText("");
                return;
            case "ans":
                txt.setText(txt.getText() + lastAnswer);
                return;
            case "="  :
                if(!txt.getText().isEmpty()){
                    try{
                        lastAnswer = com.doCalculate(txt.getText());
                        txt.setText("" + lastAnswer);
                    } catch(Exception err) {
                        txt.setText(err.getMessage());
                    } finally {
                        PresentTextIsValid = false;
                    }
                }
                return;
            case "1/x":
                name = " 1/";
                break;
            case "ln" :case "log":case "sqr":case "tan":
            case "sin":case "cos":case "exp":
                name += " ";
            }
            txt.setText(txt.getText() + name);
        }
    }
}