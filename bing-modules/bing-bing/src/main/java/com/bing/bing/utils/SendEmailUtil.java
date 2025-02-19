package com.bing.bing.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class SendEmailUtil {
    private static String ADDRESS="mailtest@mail.eyhm.com.cn";
    private static String COPYADDRESS="";
    private static String SENDERADDRESS="legaldatabase@mail.eyhm.com";
    private static String SENDERNAME="Legal Database";
    private static String PRITICTADDRESS="";
    private static String PASSWORD="z8t2fXiwFh9Y";

    protected static String genMessageID(String mailFrom) {
        // message-id 必须符合 first-part@last-part
        String[] mailInfo = mailFrom.split("@");
        String domain = mailFrom;
        int index = mailInfo.length - 1;
        if (index >= 0) {
            domain = mailInfo[index];
        }
        UUID uuid = UUID.randomUUID();
        StringBuffer messageId = new StringBuffer();
        messageId.append('<').append(uuid.toString()).append('@').append(domain).append('>');
        return messageId.toString();
    }

    public static void sendEmail(String[] emails,String title,String emailValues){
        // 配置发送邮件的环境属性
        final Properties props = new Properties();

        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtpdm.aliyun.com");
        //设置端口：
//        props.put("mail.smtp.port", "25");//或"25", 如果使用ssl，则去掉使用80或25端口的配置，进行如下配置：
        //加密方式：
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.port", "465");

        props.put("mail.smtp.from", ADDRESS);    //mailfrom 参数
        props.put("mail.user", ADDRESS);// 发件人的账号（在控制台创建的发信地址）
        props.put("mail.password", PASSWORD);// 发信地址的smtp密码（在控制台选择发信地址进行设置）
        //props.setProperty("mail.smtp.ssl.enable", "true");  //请pei'he
        System.setProperty("mail.mime.splitlongparameters", "false");//用于解决附件名过长导致的显示异常
        //props.put("mail.smtp.connectiontimeout", 1000);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        //使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        //mailSession.setDebug(true);//开启debug模式
        final String messageIDValue = genMessageID(props.getProperty("mail.user"));
        //创建邮件消息
        MimeMessage message = new MimeMessage(mailSession) {
            @Override
            protected void updateMessageID() throws MessagingException {
                //设置自定义Message-ID值
                setHeader("Message-ID", messageIDValue);//创建Message-ID
            }
        };

        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(SENDERADDRESS, SENDERNAME);//from 参数,可实现代发，注意：代发容易被收信方拒信或进入垃圾箱。
            message.setFrom(from);
            //依据提供的 eamils 数组的长度 判断是发送多人还是单人
            if(emails.length==1){
                InternetAddress to = new InternetAddress(emails[0]);
                message.setRecipient(MimeMessage.RecipientType.TO, to);
            }else {
                InternetAddress[] adds = new InternetAddress[emails.length];
                for (int i = 0; i < emails.length; i++) {
                    adds[i] = new InternetAddress(emails[i]);
                }
                message.setRecipients(MimeMessage.RecipientType.TO, adds);
            }
            message.setSentDate(new Date()); //设置时间
            String ccUser = COPYADDRESS;
            // 设置多个抄送地址
            if (null != ccUser && !ccUser.isEmpty()) {
                InternetAddress[] internetAddressCC = InternetAddress.parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            String bccUser = PRITICTADDRESS;
            // 设置多个密送地址
            if (null != bccUser && !bccUser.isEmpty()) {
                InternetAddress[] internetAddressBCC = InternetAddress.parse(bccUser);
                message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
            }
            //设置邮件标题
            message.setSubject(title);
            message.setContent(emailValues, "text/html;charset=UTF-8");//html超文本；// "text/plain;charset=UTF-8" //纯文本。
            // 发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
