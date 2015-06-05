//https://docs.oracle.com/javase/tutorial/uiswing/components/textfield.html
package digging;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener {

    public static final ArrayList<String> holder = new ArrayList<>();
    public static JTextField textField;
    public static JTextArea textArea;
    private final static String newline = "\n";
    public static String text;

    public GUI() {
        super(new GridBagLayout());
        textField = new JTextField(20);
        textField.addActionListener(this);
        textArea = new JTextArea(25, 20);
        textArea.setEditable(false);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.GREEN);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        JScrollPane scrollPane = new JScrollPane(textArea);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
        add(textField, c);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        text = textField.getText();
        setText(text);
        synchronized (holder) {
            holder.add(text);
            holder.notify();
        }
        //textField.setText("");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GUI());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public static String returntext() {
        text = textField.getText();
        return text;
    }

    public static void setText(String text) {
        textArea.append(text);
    }
    
    public static void newLine() {
        textArea.append(newline);
    }
    
    public static void clear() {
        textArea.setText("");
    }

    public static void Hold() throws InterruptedException {
        synchronized (GUI.holder) {
            while (holder.isEmpty()) {
                holder.wait();
            }
            String nextString = holder.remove(0);
        }
    }
}