/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
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
import javax.swing.JOptionPane;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import java.io.*;
import java.io.File;  
import java.io.IOException;  
import java.io.FileWriter; 
import java.util.Scanner; 

/**
 *
 * @author hellenekpo
 * @author bethanysaunders
 */
public class BankAccount {
    public static String strTransactions = "";
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
        
            
        
        // Creating Bank Statement File
        File statement;
        try {
            statement = new File("bankstatement.txt");
            if (statement.createNewFile()) {
                System.out.println("File created: " + statement.getName());
            } else {
                System.out.println("File already exists.");
            }
          } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }

        int [] account = new int[1]; // had to place the account balance in an arry
        //so that we could make references to it to update it after changes
        int [] transactions = new int[1000];
        // Instantiating an array of string transactions
        //String strTransactions = "";
        
        
        
        // the aray thats going to hold the t
        account[0] = 0; // initialize the account to 0
        ButtonMakeMoneyThread [] threads = new ButtonMakeMoneyThread[1];
        //the thread array for the threads
        JFrame jf = new JFrame("Bank Account!"); //frame for the bank account
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1200, 800);
        jf.setResizable(true);
        JButton [] buttons = new JButton[6]; //the amount of buttons in an array
        boolean [] toggle = new boolean[2]; //toggle array
        for (int i = 0; i < 2; ++i) {
            toggle[i] = true; // toggles for clocking in and out
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
        //set buttons to be a random color, set the font of the buttons, etc etc
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4, 2));
        jf.add(jp);
        JLabel [] labels = new JLabel[2];
        labels[0] = new JLabel("Hello, welcome!", SwingConstants.CENTER); //two labels for the top of the program
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
        for (int i = 2; i < 6; ++i) {
            ThreadButton thread_b = new ThreadButton(buttons[i]);
            thread_b.start();
        }
            buttons[0].addActionListener(new ButtonListen(buttons, toggle, 
                threads, account,labels[0], labels[1]));
            buttons[1].addActionListener(new ButtonListen(buttons, toggle, 
                    threads, account, labels[0], labels[1]));
            buttons[2].addActionListener(new ButtonListenWindow(buttons, account, labels[1]));
            buttons[3].addActionListener(new ButtonListenWindow(buttons, account, labels[1]));
            buttons[4].addActionListener(new ButtonListenWindow(buttons, account, labels[1]));
            buttons[5].addActionListener(new ButtonListenWindow(buttons, account, labels[1]));
            ButtonMakeMoneyThread thread_a = new ButtonMakeMoneyThread(buttons[0], account,
                    labels[0], labels[1]);
            threads[0] = thread_a;
        
    }
}


//these action listeners handles what to do if youre paying for food, buying 
class ButtonListenWindow implements ActionListener {
    JButton [] buttons;
    int [] account;
    JLabel jl1;
    //String transactions;
    File fileStatement;
    FileWriter myWriter;
    PrintWriter writer;
    File myObj = new File("bankstatement.txt");

     
    ButtonListenWindow (JButton [] curr_buttons, int [] curr_balance,
            JLabel curr_jl1) {
        account = curr_balance;
        buttons = curr_buttons;
        jl1 = curr_jl1;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton curr_button = (JButton) arg0.getSource();
        JFrame jf = new JFrame();

            if (curr_button == buttons[2]) {
            //pay rent
            if (account[0] < 1000) {
                JOptionPane.showMessageDialog(jf, "You don't have enough to"
                        + " pay rent!");
                int results = JOptionPane.showConfirmDialog(jf, "Do you want to have a negative balance?");
                if (results == 0) {
                    account[0] -= 1000;
                    String account_string = String.valueOf(account[0]);
                    jl1.setText(account_string);
                }
                else if (results == 1) {
                JOptionPane.showMessageDialog(jf, "Clock back into work to make enough"
                        + " to pay rent!");
                }
            }  
            else {
                account[0] -= 1000;
                String account_string = String.valueOf(account[0]);
                jl1.setText(account_string);
                JOptionPane.showMessageDialog(jf, "You've successfully paid rent!");
                BankAccount.strTransactions += "Rent Balance Paid : " + account_string + "\n";
                System.out.println("Successfully wrote to the file.");
            }
        }
        else if (curr_button == buttons[3]) {
            //buy food
            if (account[0] < 15) {
                JOptionPane.showMessageDialog(jf, "You don't have enough to"
                        + " buy food!");
                int results = JOptionPane.showConfirmDialog(jf, "Do you want to have a negative balance?");
                if (results == 0) {
                    JOptionPane.showMessageDialog(jf, "At least you're gonna"
                            + " eat tonight...");
                    account[0] -= 15;
                    String account_string = String.valueOf(account[0]);
                    jl1.setText(account_string);
                }
                else if (results == 1) {
                JOptionPane.showMessageDialog(jf, "Clock back into work to make enough"
                        + " to have dinner!");
                }
            }  
            else {
                account[0] -= 15;
                String account_string = String.valueOf(account[0]);
                jl1.setText(account_string);
                JOptionPane.showMessageDialog(jf, "You've successfully bought food!");
                BankAccount.strTransactions += "Food Balance Paid : " + account_string + "\n";
                System.out.println("Successfully wrote to the file.");
            }  
        }
        else if (curr_button == buttons[4]) {
            //go party
            if (account[0] < 20) {
                JOptionPane.showMessageDialog(jf, "You don't have enough to"
                        + " go PARTY!! What a bummer :(");
                int results = JOptionPane.showConfirmDialog(jf, "Do you want to have a negative balance"
                        + " so that you can have fun tonight?");
                if (results == 0) {
                    JOptionPane.showMessageDialog(jf, "We're broke but we're "
                            + "still gonna have fun!");
                    account[0] -= 20;
                    String account_string = String.valueOf(account[0]);
                    jl1.setText(account_string);
                }
                else if (results == 1) {
                JOptionPane.showMessageDialog(jf, "Clock back into work to make enough"
                        + " to have fun next time!");
                }
            }  
            else {
                account[0] -= 20;
                String account_string = String.valueOf(account[0]);
                jl1.setText(account_string);
                JOptionPane.showMessageDialog(jf, "You've bought a ticket to go out and party!");
                BankAccount.strTransactions += "Party Balance Paid : " + account_string + "\n";
                System.out.println("Successfully wrote to the file.");
            }
        }
        else if (curr_button == buttons[5]) {
            BankAccount.strTransactions += "Account Balance: " + account[0] + "\n";
            try {  
            //myWriter = new FileWriter("bankstatement.txt");
            PrintWriter writer = new PrintWriter("bankstatement.txt", "UTF-8");
            writer.write(BankAccount.strTransactions);
            writer.close();
            JOptionPane.showMessageDialog(jf, BankAccount.strTransactions);
        }
        catch(IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
        }        
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
                    Thread.currentThread().sleep(3000);
                                Random rand = new Random();
                                float r = rand.nextFloat();
                                float r1 = rand.nextFloat();
                                float g = rand.nextFloat();
                                float g2 = rand.nextFloat();
                                float b = rand.nextFloat();
                                float b3 = rand.nextFloat();
                                Color rand_color = new Color(r, g, b);
                                Color rand_color1 = new Color(r1, g2, b3);
                                button.setBackground(rand_color);
                                button.setForeground(rand_color1);
                                button.setOpaque(true);
                }
                catch (InterruptedException e) {
                    return;
                }
            }
        while (true);
      
    }
}

