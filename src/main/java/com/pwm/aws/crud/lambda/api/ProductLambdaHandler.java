package com.pwm.aws.crud.lambda.api;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import java.util.Map;

public class ProductLambdaHandler implements RequestHandler<Map<String, Object>, Object> {
   public Object handleRequest(Map<String, Object> event, Context context) {
      String userEmail = "rickyalex212@gmail.com";
      Content subject = (new Content()).withData("Your booking has been confirmed");
      Content textBody = (new Content()).withData(String.format("Hello Traveller,\n\nWe're thrilled to confirm your flight booking with AWSome-airline is booked successfully\nGet ready for an exciting adventure\n\nTo manage your booking or check in online, visit: https://awsome-airline.com \n\nImportant Information:\nPlease arrive at the airport at least 2 hours before your departure time.\nDon't forget to check the latest travel advisories and airline regulations for any necessary documentation or COVID-19 requirements.\nWe recommend downloading the airline's mobile app for real-time updates and easy check-in on the go.\nIf you have any questions or need assistance with your booking, please don't hesitate to contact us at awsome@airline.com or reply to this email.\nWe wish you a smooth and enjoyable journey!\nBest regards\nThe AWSome Airline Team"));
      Body body = (new Body()).withText(textBody);
      Message message = (new Message()).withSubject(subject).withBody(body);
      AmazonSimpleEmailService client = (AmazonSimpleEmailService)AmazonSimpleEmailServiceClientBuilder.standard().build();
      SendEmailRequest request = (new SendEmailRequest()).withDestination((new Destination()).withToAddresses(new String[]{userEmail})).withSource("awsome-airline.live@domainsbyproxy.com").withMessage(message);

      try {
         client.sendEmail(request);
         System.out.println("Email sent successfully!");
      } catch (Exception var13) {
         System.err.println("Error sending email: " + var13.getMessage());
      }

      return null;
   }
}