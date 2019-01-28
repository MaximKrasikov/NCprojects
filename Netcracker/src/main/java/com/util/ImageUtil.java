package com.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Admin on 20.12.2018.
 */
@UtilityClass
public class ImageUtil {

    public static byte[] loadImage(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(ImageUtil.class.getResource(filePath).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}