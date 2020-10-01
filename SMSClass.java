package com.example.sms;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class SMSClass {

	public static void main(String[] args) {
			
		String thisString = "";
		try 
		{
		Document doc = Jsoup.connect("https://www.imdb.com/list/ls051981806/").userAgent("mozilla/17.0").get();
		Elements temp = doc.select("div.lister-item-content");
		
		int i = 0;
		
		for(Element movieList:temp)
		{
			i++;
			thisString += (i + " " + movieList.getElementsByTag("a").first().text() + "\n");
			
		}
		System.out.println(thisString);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		/////////////////
		
		//Used for authenticating to AWS
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("XXXXXXXXXXXXXX", "XXXXXXXXXXXXXXXXXXXXXX");
		
		//Create SNS Client
		AmazonSNS snsClient = AmazonSNSClient
                .builder()
                .withRegion(Regions.US_WEST_2)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
		
		//String SMSMessage = "Hello World";
		String mobile = "+15092306279";//Enter your mobile number here
		
	   // snsClient.publish(new PublishRequest().withMessage(thisString).withPhoneNumber(mobile));
		//snsClient.publish(new PublishRequest().withTopicArn("arn:aws:sns:us-west-2:392537806034:Testing").withMessage(SMSMessage));
		
		
		
	}

}
