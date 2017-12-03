package com.company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class Menu extends JFrame{
    private JTextField email;
    private JPanel rootPanel;
    private JTextField passwordText;
    private JTextField receiver;
    private JTextField subject;
    private JTextArea text;
    private JButton send;
    private JButton receive;

    private Session sessionSend;
    private Session sessionReceive;

    public Menu(){
        super("Authentication");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        pack();
        setVisible(true);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String host = "smtp.gmail.com";
                Properties propSend = System.getProperties();
                propSend.put("mail.smtp.auth", "true");
                propSend.put("mail.smtp.starttls.enable", "true");
                propSend.put("mail.smtp.host", host);
                propSend.put("mail.smtp.port", "587");
                sessionSend = Session.getInstance(propSend,
                        new javax.mail.Authenticator() {
                            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                                return new javax.mail.PasswordAuthentication(email.getText(), passwordText.getText());
                            }
                        });
                try {
                    //Create MimeMessage object and set values
                    Message messageobj = new MimeMessage(sessionSend);
                    messageobj.setFrom(new InternetAddress(receiver.getText()));
                    messageobj.addRecipients(Message.RecipientType.TO,InternetAddress.parse(email.getText()));
                    messageobj.setSubject(subject.getText());
                    messageobj.setText(text.getText());
                    //Send the message
                    Transport.send(messageobj);
                    System.out.println("Your email sent successfully....");
                } catch (MessagingException exp) {
                    //System.out.println("MessageException");
                    //System.out.println(exp.getLocalizedMessage());
                    //String a = exp.getLocalizedMessage();
                    //if(exp.getLocalizedMessage().equals("535-5.7.8 Username and Password not accepted. Learn more at\n535 5.7.8  https://support.google.com/mail/?p=BadCredentials c1sm2440729ljb.78 - gsmtp")){
                     //   System.out.println("equals");
                    //}
                    if(exp.getLocalizedMessage().substring(0, 45).equals("535-5.7.8 Username and Password not accepted.")){
                        JOptionPane.showMessageDialog(null, "Check your email and password");
                    }
                    else {
                        exp.printStackTrace();
                    }
                }
            }
        });
        receive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String host = "smtp.gmail.com";
                Properties propReceive = System.getProperties();
                propReceive.put("mail.pop3.host", host);
                propReceive.put("mail.pop3.port", "995");
                propReceive.put("mail.pop3.starttls.enable", "true");
                sessionReceive = Session.getDefaultInstance(propReceive);
                try {
                    //Create POP3 store object and connect with the server
                    Store storeObj = sessionReceive.getStore("pop3s");
                    storeObj.connect(host, email.getText(), passwordText.getText());
                    //Create folder object and open it in read-only mode
                    Folder emailFolderObj = storeObj.getFolder("INBOX");
                    emailFolderObj.open(Folder.READ_ONLY);
                    //Fetch messages from the folder and print in a loop
                    Message[] messageobjs = emailFolderObj.getMessages();
                    new Messages(messageobjs);
                } catch (NoSuchProviderException e1) {
                    e1.printStackTrace();
                } catch (MessagingException e1) {
                    if(e1.getLocalizedMessage().equals("[AUTH] Username and password not accepted.")){
                        JOptionPane.showMessageDialog(null, "Check your email and password");
                    }
                    else {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
