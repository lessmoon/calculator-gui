package lessmoon.cal.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Main {
    public static final int WIDTH  =  300,
                            HEIGHT =  240;
    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()); 
        } catch (Exception e) {
            System.err.println("Error occurs while setting look and feel:" + e.getMessage() + ",abort.");
            return;
        }
        SwingConsole.run(new Calculator(),WIDTH,HEIGHT);
    }
}