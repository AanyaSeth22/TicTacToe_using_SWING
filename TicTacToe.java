import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JButton[] button = new JButton[9];
    private boolean xturn = true;
    private char[] board = new char[9];
    public TicTacToe(){
        frame = new JFrame("TIC-TAC-TOE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        for(int i = 0; i<9; i++){
            button[i] = new JButton();
            button[i].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            button[i].setFont(new Font("Arial", Font.PLAIN, 40));
            button[i].setBackground(Color.LIGHT_GRAY);
            button[i].addActionListener(this);  // Add action listener to each button
            panel.add(button[i]);
        }
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton)e.getSource();
        int idx = getIndex(button);
        if(xturn){
            button.setText("X");
            button.setBackground(Color.BLACK);// Set color for X
            board[idx] = 'X';
        }
        else{
            button.setText("0");
            button.setBackground(Color.BLACK);// Set color for X
            board[idx] = '0';
        }
        xturn = !xturn;
        button.setEnabled(false);
        checkForWinners();
    }
    public void checkForWinners(){
        //check for rows
        for(int i = 0; i<9; i+=3){
             if(board[i] == board[i+1] && board[i]==board[i+2] && board[i]!='\0'){
                 JOptionPane.showMessageDialog(frame, board[i] + "wins!");
                 resetGame();
                 return;
             }
        }

        //check for columns
        for(int i = 0; i<3; i++){
            if(board[i] == board[i+3] && board[i]==board[i+6] && board[i]!='\0'){
                JOptionPane.showMessageDialog(frame, board[i] + "wins!");
                resetGame();
                return;
            }
        }
        //check for diagonal
        if(board[0] == board[4] && board[0]==board[8] && board[0]!='\0'){
            JOptionPane.showMessageDialog(frame, board[0] + " " + "wins!");
            resetGame();
            return;
        }
        if(board[2] == board[4] && board[2]==board[6] && board[2]!='\0'){
            JOptionPane.showMessageDialog(frame, board[2] +" "+ "wins!");
            resetGame();
            return;
        }
        //check for tie
        boolean tie = true;
        for(int i = 0; i<9; i++){
            if(board[i] == '\0'){
                tie = false;
                break;
            }
        }
        if(tie){
            JOptionPane.showMessageDialog(frame, "Tie GAME!");
            resetGame();
        }
    }
    public void resetGame() {
        for (int i = 0; i < 9; i++) {
            button[i].setText(null);
            button[i].setEnabled(true);
            board[i] = '\0';
        }
        xturn = true;
    }
    private int getIndex(JButton but){
        for(int i = 0; i<9; i++){
            if(button[i] == but){
               return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
      new TicTacToe();
    }
}
