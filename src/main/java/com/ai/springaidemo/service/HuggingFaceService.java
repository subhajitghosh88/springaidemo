package com.ai.springaidemo.service;

import org.springframework.ai.huggingface.HuggingfaceChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HuggingFaceService {

    @Autowired
    private HuggingfaceChatModel huggingfaceChatModel;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.ai.huggingface.chat.api-key}")
    private String huggingFaceApiKey;

    /**
     * Generate text using Hugging Face API (ChatGPT)
     *
     * @param prompt
     * @return
     */
    public String generateText(String prompt) {
        return huggingfaceChatModel.call(prompt);
    }

    /**
     * Generate image using Hugging Face API (Stable Diffusion)
     *
     * @param prompt
     * @return
     */
    public byte[] generateImage(String prompt) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + huggingFaceApiKey);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String requestBody = "{\"inputs\": \"" + prompt + "\"}";

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<byte[]> response = restTemplate.exchange(
                    "https://api-inference.huggingface.co/models/openfree/flux-chatgpt-ghibli-lora",
                    HttpMethod.POST,
                    entity,
                    byte[].class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Image Not Found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating image: " + e.getMessage());
        }
    }
}
