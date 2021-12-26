package ru.itis.kpfu.telegram.config;

import lombok.Builder;
import lombok.Data;

/**
 * @author Zagir Dingizbaev
 */

@Data
@Builder
public class AWSProperties {

    private String accessKey;
    private String secretKey;
    private String endpoint;
    private String folder;
    private String bucket;
    private String region;
    private String apiSecret;
    private String queueUrl;
}
