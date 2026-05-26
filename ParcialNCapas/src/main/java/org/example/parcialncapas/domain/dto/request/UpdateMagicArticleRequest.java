package org.example.parcialncapas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.parcialncapas.domain.entity.Type;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMagicArticleRequest {
    @NotBlank
    private String name;

    @NotBlank
    private Type type;

    @NotBlank
    @Positive(message = "The price cannot be a negative number or zero")
    private Double price;

    @NotBlank
    private UUID providerId;
}
