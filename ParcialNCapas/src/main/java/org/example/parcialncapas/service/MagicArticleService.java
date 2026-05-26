package org.example.parcialncapas.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.parcialncapas.common.MagicArticleMapper;
import org.example.parcialncapas.domain.dto.request.CreateMagicArticleRequest;
import org.example.parcialncapas.domain.dto.request.UpdateMagicArticleRequest;
import org.example.parcialncapas.domain.dto.response.MagicArticleResponse;
import org.example.parcialncapas.domain.entity.MagicArticle;
import org.example.parcialncapas.domain.entity.MagicProvider;
import org.example.parcialncapas.exception.ConditionNotFulfilledException;
import org.example.parcialncapas.exception.ItemAlreadyExistsException;
import org.example.parcialncapas.exception.ResourceNotFoundException;
import org.example.parcialncapas.repository.MagicArticleRepository;
import org.example.parcialncapas.repository.MagicProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MagicArticleService {
    private final MagicArticleRepository magicArticleRepository;
    private final MagicProviderRepository magicProviderRepository;
    private final MagicArticleMapper magicArticleMapper;

    public MagicArticleResponse createMagicArticle(CreateMagicArticleRequest req) {

        if (!magicProviderRepository.existsById(req.getProviderId())){
            throw new ResourceNotFoundException("A magic provider with this id does not exists");
        }

        MagicArticle magicArticle = magicArticleMapper.toEntityCreate(req);
        MagicProvider magicProvider = magicProviderRepository.getReferenceById(magicArticle.getId());

        if (magicArticle.getType().equals(magicProvider.getType())) {
            throw new ConditionNotFulfilledException("The type of the article and the provider must be the same");
        }

        if (magicArticleRepository.existsByNameIgnoreCase(magicArticle.getName())) {
            throw new ItemAlreadyExistsException("A magic article with this name already exists");
        }

        return magicArticleMapper.toDto(
                magicArticleRepository.save(magicArticleMapper.toEntityCreate(req))
        );
    }

    public MagicArticleResponse getMagicArticleById(UUID id) {
        return magicArticleMapper.toDto(magicArticleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("A magic article with this id does not exists")));
    }

    @Transactional
    public MagicArticleResponse updateMagicArticle(UUID id, UpdateMagicArticleRequest req) {
        MagicArticleResponse article = this.getMagicArticleById(id);
        if (!magicProviderRepository.existsById(id)) {
            throw new ResourceNotFoundException("A provider with this id does not exists");
        }

        if(!magicProviderRepository.getReferenceById(id).getType().equals(req.getType())) {
            throw new ConditionNotFulfilledException("The type of the article and the provider must be the same");
        }

        return magicArticleMapper.toDto(magicArticleRepository.save(magicArticleMapper.toEntityUpdate(id, req)));
    }

    public List<MagicArticleResponse> getAllMagicArticles(String type, UUID providerId, Double precioMax){
        //No supe hacer el sort :p
        return magicArticleRepository.findAll().stream().map(magicArticleMapper::toDto).toList();
    }

    public MagicArticleResponse deleteMagicArticle(UUID id){
        MagicArticleResponse existsArticle = this.getMagicArticleById(id);
        magicArticleRepository.deleteById(id);
        return existsArticle;
    }

}
