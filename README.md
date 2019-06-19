# EmailRestApi
MicroService responsible of Asynchronous sending of Emails
# Email Rest Api Swagger Definition
```
swagger: '2.0'
info:
  description: Rest API for sending Async Emails
  version: 1.0.0
  title: Email Rest API
  
paths:
  /sendEmailAsync:
    get:
      summary: send async email
      operationId: sendEmailAsync
      description: Send an email by providing the email properties
      parameters:
      - in: query
        name: mailTo
        description: the address which the mail to be sent
        required: true
        type: string
      - in: query
        name: mailSubject
        description: mail subject
        required: false
        type: string
      - in: query
        name: mailBody
        description: mail body
        required: false
        type: string
      - in: query
        name: attachment
        description: mail attachment
        required: false
        type: string
      responses:
        200:
          description: the request has been processed
        400:
          description: problems with the calling parameters
        500:
          description: service is not working
```
