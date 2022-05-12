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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        int [] account = new int[1];
        int [] transactions = new int[1000];
        account[0] = 0;
        ButtonMakeMoneyThread [] threads = new ButtonMakeMoneyThread[8];
 
        JFrame jf = new JFrame("Bank Account!");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1200, 800);
        jf.setResizable(true);
        JButton [] buttons = new JButton[10];
        boolean [] toggle = new boolean[10];
        for (int i = 0; i < 8; ++i) {
            toggle[i] = true;
        }
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
        JLabel [] labels = new JLabel[2];
        labels[0] = new JLabel("Hello, welcome!", SwingConstants.CENTER);
        labels[1] = new JLabel("This is your bank account!",
                SwingConstants.CENTER);
        labels[0].setFont(new Font("monospaced", Font.BOLD, 50));
        labels[1].setFont(new Font("monospaced", Font.BOLD, 30));
        jp.add(labels[0]);
        jp.add(labels[1]);
        jp.add(buttons[0]);
        jp.add(buttons[1]);
        jp.add(buttons[2]);
        jp.add(buttons[3]);
        jp.add(buttons[4]);
        jp.add(buttons[5]);
        jf.setVisible(true);
        buttons[0].addActionListener(new ButtonListen(buttons, toggle, 
                threads, account,labels[0], labels[1]));
        buttons[1].addActionListener(new ButtonListen(buttons, toggle, 
                threads, account, labels[0], labels[1]));
        ButtonMakeMoneyThread thread_a = new ButtonMakeMoneyThread(buttons[0], account,
                labels[0], labels[1]);
        threads[0] = thread_a;
        ButtonMakeMoneyThread thread_b = new ButtonMakeMoneyThread(buttons[0], account,
                labels[0], labels[1]);
        threads[1] = thread_b;
        
    }
    
}

class ButtonListen implements ActionListener {
    JButton [] buttons;
    boolean [] toggles;
    ButtonMakeMoneyThread [] make_money;
    int [] account;
    JLabel jl;
    JLabel jl1;
    ButtonListen (JButton [] curr_buttons,
            boolean [] toggle, ButtonMakeMoneyThread [] makemoney, int [] curr_acc,
            JLabel curr_jl, JLabel curr_jl1) {
        buttons = curr_buttons;
        toggles = toggle;
        make_money = makemoney;
        account = curr_acc;
        jl = curr_jl;
        jl1 = curr_jl1;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        System.out.println(account[0]);
        JButton curr_button = (JButton) arg0.getSource();
        if (curr_button == buttons[0]) {
            String account_string = String.valueOf(account[0]);
            jl.setText("Current balance:");
            jl1.setText(account_string);
            if (toggles[0] == true) {
                System.out.println("this is going to accumulate money");
                buttons[0].setBackground(Color.GRAY);
                make_money[0] = new ButtonMakeMoneyThread(buttons[0], account, 
                jl, jl1);
                make_money[0].start();
                toggles[0] = false;
                toggles[1] = true;
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color rand_color = new Color(r, g, b);
                buttons[1].setBackground(rand_color);
                //turn the button gray and call the thread to make the money
            }
            if (toggles[0] == false) {
                System.out.println("its already been toggled, make the money");
            }        
        }
        if (curr_button == buttons[1]) {
            if (toggles[1] == true) {
                System.out.println("this is gonna stop it");
                System.out.println("this is the current status after it stops" + make_money[0].get_Account());
                account[0] = make_money[0].get_Account();
                make_money[0].interrupt();
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color rand_color = new Color(r, g, b);
                buttons[0].setBackground(rand_color);
                buttons[0].setOpaque(true);
                toggles[1] = false;
                toggles[0] = true;
                buttons[1].setBackground(Color.gray);   
            }
        }
    }
}


class ButtonMakeMoneyThread extends Thread {
    JButton button;
    int [] account;
    JLabel jl;
    JLabel jl1;
    ButtonMakeMoneyThread(JButton curr_button, int [] curr_account,
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
                Thread.currentThread().sleep(5000);
                account[0] += 50;
                String account_string = String.valueOf(account[0]);
                jl1.setText(account_string);
                System.out.println(account[0]);
            }
            catch (InterruptedException e) {
                return;
            }
        }
        while (true);
    }
    public int get_Account() {
        return account[0];
    }
}

class ThreadButton extends Thread {
    JButton button;
    ThreadButton(JButton curr_button) {
        button = curr_button;
    }
    @Override
    public void run() {
            do {
                try {
                    Thread.currentThread().sleep(1000);
                    Random rand = new Random();
                    float r = rand.nextFloat();
                    float g = rand.nextFloat();
                    float b = rand.nextFloat();
                    Color rand_color = new Color(r, g, b);
                    button.setBackground(rand_color);
                    button.setOpaque(true);
                }
                catch (InterruptedException e) {
                    return;
                }
            }
        while (true);
      
    }
}