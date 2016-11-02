package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.exception.EmailSendingException;

public interface MailService {

    void sendMail(String mailFrom, String mailTo, String subject, String mailText) throws EmailSendingException;

}
