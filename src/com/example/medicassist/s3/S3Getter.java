package com.example.medicassist.s3;
import java.net.URL;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;


public class S3Getter {
	// Bucket name
	private String bucketName = "medapp"; 
	// Name of the object that is going to be retrieved
	private String objectKey;
	private URL url;

	public S3Getter (String objectK) {
		objectKey = objectK;
	}
	
	// This method is for obtaining the URL (with permissions) of the object to retrieve
	public URL getPdf(){
		AmazonS3 s3client = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider());
		url = null;
		try {
			System.out.println("Generating pre-signed URL.");
			java.util.Date expiration = new java.util.Date();
			long milliSeconds = expiration.getTime();
			milliSeconds += 1000 * 60 * 60; // It will expires in one hour
			expiration.setTime(milliSeconds);

			GeneratePresignedUrlRequest generatePresignedUrlRequest = 
				    new GeneratePresignedUrlRequest(bucketName, objectKey);
			generatePresignedUrlRequest.setMethod(HttpMethod.GET); 
			generatePresignedUrlRequest.setExpiration(expiration);

			url = s3client.generatePresignedUrl(generatePresignedUrlRequest); 

		} catch (AmazonServiceException exception) {
			System.out.println("Caught an AmazonServiceException, " +
					"which means your request made it " +
					"to Amazon S3, but was rejected with an error response " +
			"for some reason.");
			System.out.println("Error Message: " + exception.getMessage());
			System.out.println("HTTP  Code: "    + exception.getStatusCode());
			System.out.println("AWS Error Code:" + exception.getErrorCode());
			System.out.println("Error Type:    " + exception.getErrorType());
			System.out.println("Request ID:    " + exception.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, " +
					"which means the client encountered " +
					"an internal error while trying to communicate" +
					" with S3, " +
			"such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		return url;
	}
}