/*Tic-Tac-Toe Game with a simple Graphical User Interface (GUI)
 * Originally from http://kodingtutorials.weebly.com/tic-tac-toe-gui.html
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.*;
import java.awt.Color;

public class TicTacToe extends JPanel
{
    JButton buttons[][] = new JButton[3][3]; 
    int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O
    
    public TicTacToe()
    {
      setLayout(new GridLayout(3,3));
      initializeButtons(); 
    }
    
    public void initializeButtons()
    {
        for(int r = 0; r < 3; r++)
        {
          for (int c = 0; c < 3; c++)
          {
            buttons[r][c] = new JButton();
            buttons[r][c].setText("");
            buttons[r][c].addActionListener(new buttonListener());
            buttons[r][c].setFont(new Font ("Arial", Font.BOLD, 60));
            
            add(buttons[r][c]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already    
          }
        }
    }
    public void resetButtons()
    {
      for(int r = 0; r < 3; r++)
        {
          for (int c = 0; c < 3; c++)
          {
            buttons[r][c].setText("");
          }
        }
      alternate = 0;
    }
    
// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    { 
        public void actionPerformed(ActionEvent e) 
        {
            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if (buttonClicked.getText().length() > 0)
            {
              JOptionPane.showMessageDialog(null, "You can't do that!", 
                                              "Yo, Doofus!!", JOptionPane.WARNING_MESSAGE);
            }
            else if (alternate%2 == 0)
            {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.ORANGE);
                alternate++;
            }
            else
            {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.PINK);
                alternate++;
            }
            
            if(checkForWin() == true)
            {
                JOptionPane.showConfirmDialog(null, "Game Over.", 
                                              "Click OK", JOptionPane.DEFAULT_OPTION);
                resetButtons();
            }
        }
        
        public boolean checkForWin()
        {
            //horizontal win check
          if (checkThree(buttons[0][0].getText(), 
                         buttons[0][1].getText(), 
                         buttons[0][2].getText()))
             return true;
          else if (checkThree(buttons[1][0].getText(),
                              buttons[1][1].getText(), 
                              buttons[1][2].getText())) 
             return true; 
          else if (checkThree(buttons[2][0].getText(), 
                              buttons[2][1].getText(), 
                              buttons[2][2].getText())) 
             return true;
            
            //vertical win check
          else if (checkThree(buttons[0][0].getText(), 
                              buttons[1][0].getText(), 
                              buttons[2][0].getText()))
             return true;
          else if (checkThree(buttons[0][1].getText(), 
                              buttons[1][1].getText(), 
                              buttons[2][1].getText())) 
             return true; 
          else if (checkThree(buttons[0][2].getText(), 
                              buttons[1][2].getText(), 
                              buttons[2][2].getText())) 
             return true;

            //diagonal win check
           else if (checkThree(buttons[0][0].getText(), 
                               buttons[1][1].getText(), 
                               buttons[2][2].getText()))
             return true;
           else if (checkThree(buttons[0][2].getText(), 
                               buttons[1][1].getText(), 
                               buttons[2][0].getText()))
             return true;
           
           //if not a win, check for tie
           if (alternate == 9)
             return true;
           else
             return false;
            
        }
        
        public boolean checkThree (String s1, String s2, String s3)
        {
            if (s1.equals(s2) && s1.equals(s3) && s1.length() > 0 )
              return true;
            else
              return false;
        }
        
    }
    
    public static void main(String[] args) 
    {
        JFrame window = new JFrame("Tic-Tac-Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.setBounds(300,200,300,300);
        window.setVisible(true);
    }
}
