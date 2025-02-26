/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.srijan.pixelcypher.pixelCypher;

import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 *
 * @author srijan
 */

@RestController
public class MyController {

@GetMapping("/decode")
public String getMethodName(@RequestParam String param) {
    return new String();
}
@RequestMapping(value = "/encode", method = RequestMethod.GET)
public String requestMethodName(@RequestParam BufferedImage img,@RequestParam String text,@RequestParam String outputPath) {
    return new String();
}



}
