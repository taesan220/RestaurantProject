package test;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class ScrollbarTest extends JFrame implements ActionListener {
protected JTextField textField;
protected JTextArea textArea;
public ScrollbarTest() {
setTitle("Text Area Test");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
textField = new JTextField(30);
textField.addActionListener(this);
textArea = new JTextArea(10, 30);
textArea.setEditable(false);
JScrollPane scrollPane= new JScrollPane(textArea);
add(textField, BorderLayout.NORTH);
add(scrollPane, BorderLayout.CENTER);
pack();
setVisible(true);
}
public void actionPerformed(ActionEvent evt) {
String text = textField.getText();
textArea.append(text + "\n");
textField.selectAll();
textArea.setCaretPosition(textArea.getDocument().getLength());
}

public static void main(String[] args) {
new ScrollbarTest();
}
}