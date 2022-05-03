/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankaccount;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

/**
 *
 * @author hellenekpo
 */
public class BankAccount {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //implement a bank account software that would emulate a user
        //spending money on different options, 
        //accumulating money every so often from their job or part-time 
        //small jobs, and then tracking the transaction information. 
        //The three concepts we would like to use are
        //graphics, file i/o and thread concurrency.
        System.out.println("hekllllo");
        JFrame jf = new JFrame("Bank Account!");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1200, 800);
        jf.setResizable(false);
        JButton [] buttons = new JButton[10];
        boolean [] toggle = new boolean[10];
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout(4, 2));
        jf.add(jp);
        jf.setVisible(true);
        for (int i = 0; i < 10; ++i) {
            JButton jb = new JButton("Click here");
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color rand_color = new Color(r, g, b);
            jb.setBackground(rand_color);
            jb.setOpaque(true);
            jb.setBorderPainted(false);
            buttons[i] = jb;
            jp.add(buttons[i]);
        }
        
        
        
    }
    
}
