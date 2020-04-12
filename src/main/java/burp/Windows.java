package burp;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

public class Windows{

    public static void Windows(String code) {
        JFrame windows = new JFrame();
        windows.setVisible(true);
        windows.setTitle("Copy2Java - Code By CoolCat");
        //windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windows.setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        windows.setResizable(false);
        windows.setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel proxtlabel = new JLabel("Proxy:");
        proxtlabel.setBounds(10, 236, 48, 25);
        contentPane.add(proxtlabel);

        JTextField proxy = new JTextField();
        proxy.setText("127.0.0.1:8080");
        proxy.setBounds(59, 233, 139, 28);
        contentPane.add(proxy);
        proxy.setColumns(10);

        final JCheckBox setproxy = new JCheckBox("set");
        setproxy.setBounds(204, 232, 48, 29);
        contentPane.add(setproxy);

        JButton toclip = new JButton("Copy to clipboard");

        toclip.setBounds(258, 232, 164, 29);
        toclip.setVisible(true);
        contentPane.add(toclip);

        //代码面板
        final JTextArea textArea = new JTextArea();
        //textArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0, 0, 434, 226);
        contentPane.add(scrollPane);

        textArea.setText(code);

        setproxy.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                //System.out.println(setproxy.isSelected());
                String[] proxyStr = proxy.getText().split(":");

                if(setproxy.isSelected()) {
                    String code = textArea.getText().toString().replace("setProxy(false)", "setProxy(true)");
                    code = code.replace("127.0.0.1",proxyStr[0]).replace("8080",proxyStr[1]);
                    textArea.setText(code);
                }else {
                    String code = textArea.getText().toString().replace("setProxy(true)", "setProxy(false)");
                    textArea.setText(code);
                }
            }
        });


        toclip.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard ();
                clip.setContents(new StringSelection(textArea.getText()), null);
            }
        });
    }

    public static void show(String code) {
        Windows(code);
    }
}

