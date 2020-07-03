package br.com.teamregister.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.apache.commons.compress.compressors.gzip.GzipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

@Component
public class S3ClientFacade {

    private static final String IMAGE_FOLDER = "images/";

    @Autowired
    private Environment env;

    private AmazonS3 s3client;

    S3ClientFacade() {
        s3client = AmazonS3ClientBuilder.defaultClient();
    }

    public String saveImage(File file) {
        String key = UUID.randomUUID().toString();
        s3client.putObject(env.getProperty("s3.bucketname"), IMAGE_FOLDER + key, file);

        return key;
    }

}