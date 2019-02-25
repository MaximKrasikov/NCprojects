package com.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Created by Admin on 25.02.2019.
 */
@UtilityClass
public class ImageUtil {

    public static byte[] loadImage(String filePath) {

        try {
            // return Files.readAllBytes(Paths.get(ImageUtil.class.getResource(filePath).toURI()));
            return IOUtils.toByteArray(ImageUtil.class.getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
