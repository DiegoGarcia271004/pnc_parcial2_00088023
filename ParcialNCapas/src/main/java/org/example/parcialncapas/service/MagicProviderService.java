package org.example.parcialncapas.service;

import lombok.RequiredArgsConstructor;
import org.example.parcialncapas.repository.MagicProviderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MagicProviderService {
    private final MagicProviderRepository providerRepository;

    
}
