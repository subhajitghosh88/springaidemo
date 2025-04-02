package com.ai.springaidemo.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerateImagesResponse;
import com.google.genai.types.GeneratedImage;
import com.google.genai.types.Image;
import org.apache.http.HttpException;
import org.springframework.ai.huggingface.HuggingfaceChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class GeminiAIService {

    @Autowired
    private Client client;

    @Value("${gemini.ai.model}")
    private String model;

    @Autowired
    private HuggingfaceChatModel huggingfaceChatModel;

    /**
     * Generate text using Gemini AI API
     *
     * @param prompt
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public String generateText(String prompt) throws HttpException, IOException {
        GenerateContentResponse response = client.models.generateContent(model, prompt, null);
        return response.text();
    }

    /**
     * Generate image using Gemini AI API
     *
     * @param prompt
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public byte[] generateImage(String prompt) throws HttpException, IOException {
        GenerateImagesResponse response = client.models.generateImages(model, prompt, null);
        Optional<Image> optionalImage = response.generatedImages().get().stream().findFirst().flatMap(GeneratedImage::image);

        if (optionalImage.isPresent()) {
            return optionalImage.get().imageBytes().map(bytes -> Base64.getDecoder().decode(bytes))
                    .orElseThrow(() -> new IOException("No image found in the response"));
        } else
            throw new IOException("No image found in the response");
    }
}
