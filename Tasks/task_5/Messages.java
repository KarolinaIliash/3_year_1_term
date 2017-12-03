package com.company;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import java.io.IOException;

public class Messages extends JFrame{
    private JTextArea msg;
    private JPanel rootPanel;
    Message[] messages;

    public Messages(Message[] messages){
        super("Messages");
        setContentPane(rootPanel);
        pack();
        setVisible(true);
        this.messages = messages;
        try {
            for (int i = 0; i < messages.length; i++) {
                msg.append("№ " + i + "\n");
                msg.append("Sender: " + messages[i].getFrom()[0] + "\n");
                msg.append("Date: " + messages[i].getSentDate() + "\n");
                msg.append("Email Subject: " + messages[i].getSubject() + "\n");
                msg.append("Content: " + messages[i].getContent() + "\n");
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
