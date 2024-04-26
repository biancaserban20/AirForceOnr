package com.pweb.AirForceOne.service;

import com.pweb.AirForceOne.dto.ClientDto;
import com.pweb.AirForceOne.exceptions.ClientExistsException;
import com.pweb.AirForceOne.exceptions.ClientNotFound;
import com.pweb.AirForceOne.exceptions.WrongPasswordException;
import com.pweb.AirForceOne.model.Client;
import com.pweb.AirForceOne.repository.BookingRepository;
import com.pweb.AirForceOne.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void createClient(ClientDto clientDto) {
        var dbClient = clientRepository.findByEmail(clientDto.getEmail());
        if (dbClient.isPresent()) {
            throw new ClientExistsException();
        }

        var newClient = modelMapper.map(clientDto, Client.class);
        newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
        newClient.setCreatedAt(ZonedDateTime.now());
        clientRepository.save(newClient);
    }

    public void loginClient(ClientDto clientDto){
        var client = clientRepository
                .findByEmail(clientDto.getEmail())
                .orElseThrow(ClientNotFound::new);

        if(!passwordEncoder.matches(clientDto.getPassword(), client.getPassword())){
            throw new WrongPasswordException();
        }
    }

    public ClientDto getClientData(String email){
        return modelMapper.map(
                clientRepository.findByEmail(email).orElseThrow(ClientNotFound::new),
                ClientDto.class
        );
    }

    public void deleteClient(String email) {
        var client = clientRepository.findByEmail(email).orElseThrow(ClientNotFound::new);
        // for each booking, make the client_id null
        bookingRepository.findAllByClient(client.getId()).forEach( booking -> booking.setClient(null));
        clientRepository.delete(client);
    }
}
