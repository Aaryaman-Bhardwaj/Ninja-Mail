package com.aaryaman.ninjamail.model;

import java.util.Collections;
import java.util.List;


public class Personalization
{

    List<Mail> to;
    String subject;

    public Personalization(String subject, Mail to)
    {
        this.subject = subject;
        this.to = Collections.singletonList(to);
    }

    public List<Mail> getTo()
    {
        return this.to;
    }

    public void setTo(List<Mail> to)
    {
        this.to = to;
    }

    public String getSubject()
    {
        return this.subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }
}
