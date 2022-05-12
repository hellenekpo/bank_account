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
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.SwingConstants;
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
        JButton jb = new JButton("Welcome! Clock In");
        buttons[0] = jb;
        JButton jb1 = new JButton("Clock Out");
        buttons[1] = jb1;
        JButton jb2 = new JButton("Pay Rent");
        buttons[2] = jb2;
        JButton jb3 = new JButton("Buy Food");
        buttons[3] = jb3;
        JButton jb4 = new JButton("Go Party");
        buttons[4] = jb4;
        JButton jb5 = new JButton("Print Bank Statement");
        buttons[5] = jb5;
        for (int i = 0; i < 6; ++i) {
            buttons[i].setPreferredSize(new Dimension(200, 100));
            buttons[i].setFont(new Font("monospaced", Font.BOLD, 50));
            Random rand = new Random();
            float r = rand.nextFloat();
            float r1 = rand.nextFloat();
            float g = rand.nextFloat();
            float g2 = rand.nextFloat();
            float b = rand.nextFloat();
            float b3 = rand.nextFloat();
            Color rand_color = new Color(r, g, b);
            Color rand_color1 = new Color(r1, g2, b3);
            buttons[i].setBackground(rand_color);
            buttons[i].setForeground(rand_color1);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
        }
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4, 2));
        jf.add(jp);
        JLabel jl = new JLabel("Hello, welcome!", SwingConstants.CENTER);
        JLabel jl1 = new JLabel("This is your bank account!",
                SwingConstants.CENTER);
        jl.setFont(new Font("monospaced", Font.BOLD, 50));
        jl1.setFont(new Font("monospaced", Font.BOLD, 30));
        jp.add(jl);
        jp.add(jl1);
        jp.add(buttons[0]);
        jp.add(buttons[1]);
        jp.add(buttons[2]);
        jp.add(buttons[3]);
        jp.add(buttons[4]);
        jp.add(buttons[5]);
        jf.setVisible(true);
       
        
        
        
    }
    
}

class ButtonMakeMoney extends Thread {
    JButton button;
    int account;
    JLabel jl;
    JLabel jl1;
    ButtonMakeMoney(JButton curr_button, int curr_account,
            JLabel curr_jl, JLabel curr_jl1) {
        button = curr_button;
        account = curr_account;
        jl = curr_jl;
        jl1 = curr_jl1;
    }
    @Override
    public void run() {
        do {
            try {
                Thread.currentThread().sleep(10000);
            }
            catch (InterruptedException e) {
                return;
            }
        }
        while (true);
    }
}
