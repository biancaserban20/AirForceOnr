package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.model.Client;
import com.pweb.AirForceOne.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public void insertClient(Client client) {
        clientRepository.save(client);
    }
}
