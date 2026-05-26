package org.example.parcialncapas.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.parcialncapas.domain.entity.Type;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMagicProviderRequest {
    @NotBlank
    private String name;

    @NotBlank
    private Type type;
}
