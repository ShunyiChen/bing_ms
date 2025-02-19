package com.ruoyi.filingsystem.service.impl;

import com.ruoyi.filingsystem.service.IFilingEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FilingEmailServiceImpl implements IFilingEmailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public FilingEmailServiceImpl(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendReminderEmailForOverdueUsers(String subject, String staffId, String staffName, String staffEmail, String filingRoom, List<String> files) throws MessagingException {
        Context context = new Context();
        context.setVariable("staffName", staffName);
        context.setVariable("staffId", staffId);
        context.setVariable("filingRoom", filingRoom);
        context.setVariable("total", files.size());
        context.setVariable("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        context.setVariable("files", files);

        String emailContent = templateEngine.process("reminderEmailForOverdueUsers", context);
        // 创建 MIME 邮件对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(staffEmail);
        helper.setFrom("FilingSystem@mail.eyhm.com.cn");
        helper.setSubject(subject);
        helper.setText(emailContent, true); // true 表示邮件内容是 HTML

        // 发送邮件
        mailSender.send(mimeMessage);
    }

    public void sendReminderEmailForBorrowingUsers(String subject, String staffId, String staffName, String staffEmail, String filingRoom, List<String> files) throws MessagingException {
        Context context = new Context();
        context.setVariable("staffName", staffName);
        context.setVariable("staffId", staffId);
        context.setVariable("filingRoom", filingRoom);
        context.setVariable("total", files.size());
        context.setVariable("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        context.setVariable("files", files);

        String emailContent = templateEngine.process("notifyOfBoxBorrowing", context);
        // 创建 MIME 邮件对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(staffEmail);
        helper.setFrom("FilingSystem@mail.eyhm.com.cn");
        helper.setSubject(subject);
        helper.setText(emailContent, true); // true 表示邮件内容是 HTML

        // 发送邮件
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendReminderEmailForReturningUsers(String subject, String staffId, String staffName, String staffEmail, String filingRoom, List<String> files) throws MessagingException {
        Context context = new Context();
        context.setVariable("staffName", staffName);
        context.setVariable("staffId", staffId);
        context.setVariable("filingRoom", filingRoom);
        context.setVariable("total", files.size());
        context.setVariable("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        context.setVariable("files", files);

        String emailContent = templateEngine.process("notifyOfBoxReturning", context);
        // 创建 MIME 邮件对象
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(staffEmail);
        helper.setFrom("FilingSystem@mail.eyhm.com.cn");
        helper.setSubject(subject);
        helper.setText(emailContent, true); // true 表示邮件内容是 HTML

        // 发送邮件
        mailSender.send(mimeMessage);
    }
}
