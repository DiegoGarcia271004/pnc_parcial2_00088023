package org.example.parcialncapas.common;

import lombok.RequiredArgsConstructor;
import org.example.parcialncapas.domain.dto.request.CreateMagicArticleRequest;
import org.example.parcialncapas.domain.dto.request.UpdateMagicArticleRequest;
import org.example.parcialncapas.domain.dto.response.MagicArticleResponse;
import org.example.parcialncapas.domain.entity.MagicArticle;
import org.example.parcialncapas.domain.entity.MagicProvider;
import org.example.parcialncapas.repository.MagicProviderRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MagicArticleMapper {

    private final MagicProviderRepository magicProviderRepository;

    public MagicArticle toEntityCreate(CreateMagicArticleRequest req) {

        MagicProvider provider = magicProviderRepository.getReferenceById(req.getProviderId());

        return MagicArticle.builder()
                .name(req.getName())
                .price(req.getPrice())
                .type(req.getType())
                .provider(provider)
                .build();
    }

    public MagicArticle toEntityUpdate(UUID id, UpdateMagicArticleRequest req) {

        MagicProvider provider = magicProviderRepository.getReferenceById(req.getProviderId());

        return MagicArticle.builder()
                .id(id)
                .name(req.getName())
                .price(req.getPrice())
                .type(req.getType())
                .provider(provider)
                .build();
    }

    public MagicArticleResponse toDto(MagicArticle entity) {
        return MagicArticleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .price(entity.getPrice())
                .provider(entity.getProvider())
                .build();
    }


}
