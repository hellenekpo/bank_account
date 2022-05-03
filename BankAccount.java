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
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
        jf.setResizable(true);
        JButton [] buttons = new JButton[10];
        boolean [] toggle = new boolean[10];
        for (int i = 0; i < 10; ++i) {
            JButton jb = new JButton("Welcome! Clock In");
            jb.setPreferredSize(new Dimension(200, 100));
            jb.setFont(new Font("monospaced", Font.BOLD, 50));
            Random rand = new Random();
            float r = rand.nextFloat();
            float r1 = rand.nextFloat();
            float g = rand.nextFloat();
            float g2 = rand.nextFloat();
            float b = rand.nextFloat();
            float b3 = rand.nextFloat();
            Color rand_color = new Color(r, g, b);
            Color rand_color1 = new Color(r1, g2, b3);
            jb.setBackground(rand_color);
            jb.setForeground(rand_color1);
            jb.setOpaque(true);
            jb.setBorderPainted(false);
            buttons[i] = jb;
        }
        JPanel jp = new JPanel();
    
        jp.setLayout(new BorderLayout(4, 2));
        jf.add(jp);
        jp.add(buttons[0], BorderLayout.PAGE_START);
        jp.add(buttons[1], BorderLayout.CENTER);
        jp.add(buttons[2], BorderLayout.EAST);
        jp.add(buttons[3], BorderLayout.WEST);
        jp.add(buttons[4], BorderLayout.PAGE_END);
        jf.setVisible(true);
       
        
        
        
    }
    
}
