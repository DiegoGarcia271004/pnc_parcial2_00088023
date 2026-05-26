package org.example.parcialncapas.controller;

import lombok.RequiredArgsConstructor;
import org.example.parcialncapas.domain.dto.request.CreateMagicArticleRequest;
import org.example.parcialncapas.domain.dto.request.CreateMagicProviderRequest;
import org.example.parcialncapas.domain.dto.request.UpdateMagicArticleRequest;
import org.example.parcialncapas.domain.dto.response.GeneralResponse;
import org.example.parcialncapas.domain.entity.Type;
import org.example.parcialncapas.service.MagicArticleService;
import org.example.parcialncapas.service.MagicProviderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GeneralController {
    private final MagicArticleService magicArticleService;
    private final MagicProviderService magicProviderService;

    @PostMapping("/provider")
    public ResponseEntity<GeneralResponse> createProvider(@RequestBody CreateMagicProviderRequest req) {
        return buildResponse("Magic provider created successfully",
        HttpStatus.CREATED,
        magicProviderService.createMagicProvider(req));
    }

    @PostMapping("/artefacts")
    public ResponseEntity<GeneralResponse> createArticleBy(@RequestBody CreateMagicArticleRequest req) {
        return buildResponse(
                "Magic article created successfully",
                HttpStatus.CREATED,
                magicArticleService.createMagicArticle(req)
        );
    }

    @GetMapping("/artefacts")
    public ResponseEntity<GeneralResponse> getAllMagicArticle(
            @RequestParam(defaultValue = "POCION") Type type,
            @RequestParam(defaultValue = "") UUID id,
            @RequestParam(defaultValue = "") Double precioMax
    ) {
        return buildResponse(
                "Magic articles founded",
                HttpStatus.OK,
                magicArticleService.getAllMagicArticles(type.name(), id, precioMax)
        );
    }

    @GetMapping("/artefacts/{id}")
    public ResponseEntity<GeneralResponse> getMagicalArticleById(@PathVariable UUID id) {
        return buildResponse("Magic article found", HttpStatus.OK, magicArticleService.getMagicArticleById(id));
    }

    @PutMapping("/artefacts/{id}")
    public ResponseEntity<GeneralResponse> updateMagicalArticle(@PathVariable UUID id, @RequestBody UpdateMagicArticleRequest req) {
        return buildResponse(
                "Article updated Successfully",
                HttpStatus.OK,
                magicArticleService.updateMagicArticle(id, req)
        );
    }

    @DeleteMapping("/artefacts/{id}")
    public ResponseEntity<GeneralResponse> deleteMagicalArticle(@PathVariable UUID id) {
        return buildResponse(
                "Magical Article deleted successfully",
                HttpStatus.OK,
                magicArticleService.deleteMagicArticle(id)
        );
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        return ResponseEntity
                .status(status)
                .body(GeneralResponse.builder()
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
                );
    }

}
