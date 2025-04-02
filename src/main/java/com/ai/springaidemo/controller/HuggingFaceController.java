package com.ai.springaidemo.controller;

import com.ai.springaidemo.service.HuggingFaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/huggingface")
@Tag(name = "Hugging Face API", description = "Endpoints for interacting with Hugging Face AI")
public class HuggingFaceController {

    @Autowired
    private HuggingFaceService huggingFaceService;

    @GetMapping("/generate-text")
    @Operation(summary = "Generate Text", description = "Generates text based on the provided prompt")
    public String generateText(@RequestParam String prompt) {
        try {
            return huggingFaceService.generateText(prompt);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/generate-image")
    @Operation(summary = "Generate AI image", description = "Generates an image from a text prompt using Hugging Face")
    public ResponseEntity<byte[]> generateImage(@RequestParam String prompt) {
        byte[] imageBytes = huggingFaceService.generateImage(prompt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok().headers(headers).body(imageBytes);
    }
}
