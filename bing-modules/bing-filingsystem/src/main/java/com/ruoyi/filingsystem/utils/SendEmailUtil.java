package com.ruoyi.filingsystem.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class SendEmailUtil {
    private static String SENDERNAME = "";
    private static String ADDRESS = "FilingSystem@mail.eyhm.com.cn";
    private static String PASSWORD = "stu6Z07VfHWW";

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

    public static void sendEmail(String[] emails, String title, String emailValues, String ccEmail) {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtpdm.aliyun.com");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.from", ADDRESS);    //mailfrom 参数
        props.put("mail.user", ADDRESS);// 发件人的账号（在控制台创建的发信地址）
        props.put("mail.password", PASSWORD);// 发信地址的smtp密码（在控制台选择发信地址进行设置）
        props.setProperty("mail.smtp.ssl.enable", "true");  //请pei'he
        System.setProperty("mail.mime.splitlongparameters", "false");//用于解决附件名过长导致的显示异常

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
            InternetAddress from = new InternetAddress(ADDRESS, SENDERNAME);
            message.setFrom(from);
            //依据提供的 eamils 数组的长度 判断是发送多人还是单人
            if (emails.length == 1) {
                InternetAddress to = new InternetAddress(emails[0]);
                message.setRecipient(MimeMessage.RecipientType.TO, to);
            } else {
                InternetAddress[] adds = new InternetAddress[emails.length];
                for (int i = 0; i < emails.length; i++) {
                    adds[i] = new InternetAddress(emails[i]);
                }
                message.setRecipients(MimeMessage.RecipientType.TO, adds);
            }
            message.setSentDate(new Date()); //设置时间
            String ccUser = ccEmail.substring(0, ccEmail.length() - 1);
            // 设置多个抄送地址
            if (null != ccUser && !ccUser.isEmpty()) {
                @SuppressWarnings("static-access")
                InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            //设置邮件标题
            message.setSubject(title);
            message.setContent(emailValues, "text/html;charset=UTF-8");//html超文本；// "text/plain;charset=UTF-8" //纯文本。
            Transport.send(message);
        } catch (MessagingException e) {
            String err = e.getMessage();
            // 在这里处理message内容， 格式是固定的
            System.out.println(err);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}