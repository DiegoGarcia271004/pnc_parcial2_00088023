package org.example.parcialncapas.domain.dto.response;

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
public class MagicProviderResponse {
    private UUID id;

    private String name;

    private Type type;
}
