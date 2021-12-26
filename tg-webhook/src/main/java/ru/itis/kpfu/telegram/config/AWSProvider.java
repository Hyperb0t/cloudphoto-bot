package ru.itis.kpfu.telegram.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.Getter;

public class AWSProvider {

    @Getter
    private final AWSProperties properties;

    public AWSProvider() {
        this.properties = AWSProperties.builder()
                .endpoint("storage.yandexcloud.net")
                .region("ru-central1")
                .accessKey(System.getenv("AWS_ACCESS_KEY_ID"))
                .secretKey(System.getenv("AWS_SECRET_ACCESS_KEY"))
                .apiSecret(System.getenv("API_SECRET"))
                .folder(System.getenv("AWS_FOLDER_ID"))
                .bucket(System.getenv("BUCKET"))
                .queueUrl(System.getenv("AWS_QUEUE_URL"))
                .build();
        System.out.println(properties);
    }

    public AmazonS3 createClient() {
        AWSCredentials credentials;
        if (properties.getAccessKey() != null && properties.getSecretKey() != null)
            credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());
        else {
            credentials = new ProfileCredentialsProvider().getCredentials();
        }

        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setProtocol(Protocol.HTTP);

        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(
                properties.getEndpoint(), properties.getRegion());

        return AmazonS3ClientBuilder.standard()
                .withPathStyleAccessEnabled(true)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withClientConfiguration(clientConfig)
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }

}
