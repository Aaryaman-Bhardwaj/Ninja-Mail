package com.aaryaman.ninjamail.model;


import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class EmailRequestSendgrid
{
    List<Personalization> personalizations;
    List<Content> content;
    Mail from;
    Mail reply_to;



    public EmailRequestSendgrid(Content content, Mail from, Personalization personalization, Mail replyTo)
    {
        this.content = Collections.singletonList(content);
        this.from = from;
        this.personalizations = Collections.singletonList(personalization);
        this.reply_to = replyTo;
    }

    public EmailRequestSendgrid()
    {
    }


    public List<Personalization> getPersonalizations()
    {
        return this.personalizations;
    }

    public void setPersonalizations(List<Personalization> personalizations)
    {
        this.personalizations = personalizations;
    }

    public List<Content> getContent()
    {
        return this.content;
    }

    public void setContent(List<Content> content)
    {
        this.content = content;
    }

    public Mail getFrom()
    {
        return this.from;
    }

    public void setFrom(Mail from)
    {
        this.from = from;
    }

    public Mail getReply_to()
    {
        return this.reply_to;
    }

    public void setReply_to(Mail reply_to)
    {
        this.reply_to = reply_to;
    }
}
