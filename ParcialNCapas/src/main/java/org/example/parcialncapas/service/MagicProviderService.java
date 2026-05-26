package org.example.parcialncapas.service;

import lombok.RequiredArgsConstructor;
import org.example.parcialncapas.common.MagicArticleMapper;
import org.example.parcialncapas.domain.dto.request.CreateMagicProviderRequest;
import org.example.parcialncapas.domain.dto.response.MagicProviderResponse;
import org.example.parcialncapas.repository.MagicProviderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MagicProviderService {
    private final MagicProviderRepository providerRepository;
    private final MagicArticleMapper magicArticleMapper;

    public MagicProviderResponse createMagicProvider(CreateMagicProviderRequest req) {
        return magicArticleMapper.toDto(providerRepository.save(magicArticleMapper.toEntityCreate(req)));
    }


}
