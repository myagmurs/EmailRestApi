package com.email.app.model;

public class Email {

	private String mailTo; //could be multi user // could be cc bcc // could be from
	private String mailSubject;
	private String mailBody;
	private String mailAttachmentURI;
	
	public Email() {
		super();
	}

	public Email(String mailTo, String mailSubject, String mailBody, String mailAttachmentURI) {
		this.mailTo = mailTo;
		this.mailSubject = mailSubject;
		this.mailBody = mailBody;
		this.mailAttachmentURI = mailAttachmentURI;
	}

	public String getMailTo() { 
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getMailAttachmentURI() {
		return mailAttachmentURI;
	}

	public void setMailAttachmentURI(String mailAttachmentURI) {
		this.mailAttachmentURI = mailAttachmentURI;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
