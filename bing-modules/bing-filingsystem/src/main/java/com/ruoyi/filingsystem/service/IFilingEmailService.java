package com.ruoyi.filingsystem.service;

import jakarta.mail.MessagingException;

import java.util.List;

public interface IFilingEmailService {

    void sendReminderEmailForOverdueUsers(String subject,
                                          String staffId,
                                          String staffName,
                                          String staffEmail,
                                          String filingRoom,
                                          List<String> files) throws MessagingException;

    void sendReminderEmailForBorrowingUsers(String subject,
                                          String staffId,
                                          String staffName,
                                          String staffEmail,
                                          String filingRoom,
                                          List<String> files) throws MessagingException;

    void sendReminderEmailForReturningUsers(String subject,
                                            String staffId,
                                            String staffName,
                                            String staffEmail,
                                            String filingRoom,
                                            List<String> files) throws MessagingException;
}
