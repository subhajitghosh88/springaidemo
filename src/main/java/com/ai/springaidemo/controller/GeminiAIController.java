package com.ai.springaidemo.controller;

import com.ai.springaidemo.service.GeminiAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/gemini")
@Tag(name = "Gemini API", description = "Endpoints for interacting with Gemini AI")
public class GeminiAIController {

    @Autowired
    private GeminiAIService geminiAIService;

    @GetMapping("/generate-text")
    @Operation(summary = "Generate Text", description = "Generates text based on the provided prompt")
    public String generateText(@RequestParam String prompt) {
        try {
            return geminiAIService.generateText(prompt);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/generate-image")
    @Operation(summary = "Generate AI image", description = "Generates an image from a text prompt using Gemini")
    public ResponseEntity<byte[]> generateImage(@RequestParam String prompt) throws HttpException, IOException {
        byte[] imageBytes = geminiAIService.generateImage(prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }
}
