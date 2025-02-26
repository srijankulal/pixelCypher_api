package com.srijan.pixelcypher.pixelCypher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;

import com.srijan.pixelcypher.pixelCypher.ImageSteganography.ImageSteganography;


@RestController
@RequestMapping("/api") // Base URL for API
public class MyController {
    @PostMapping("/encode")
    public ResponseEntity<byte[]> encodeTextInImage(@RequestParam("image") MultipartFile imageFile,
                                                    @RequestParam("text") String text) {
        try {
            // Read the image from request
            BufferedImage image = ImageIO.read(imageFile.getInputStream());

            // Call your encoding logic (Modify the image)
            BufferedImage encodedImage = ImageSteganography.cypher(image, text, "output.png");

            // Convert BufferedImage to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(encodedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            // Set response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/png");

            // Return the image as ResponseEntity<byte[]>
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @PostMapping("/decode")
    public String decodeTextFromImage(@RequestParam("image") MultipartFile imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile.getInputStream());
            String text= ImageSteganography.decypher(image);
            return "Decoded text: " + text;
        } catch (IOException e) {
            return "Error decoding image.";
        }
    }
}
