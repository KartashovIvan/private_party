package org.javaacademy.private_party.service;

import lombok.AllArgsConstructor;
import org.javaacademy.private_party.dto.GuestDto;
import org.javaacademy.private_party.repository.PrivatePartyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PrivatePartyService {
    private PrivatePartyRepository privatePartyRepository;

    public void addGuest(String login, String pass, GuestDto guestDto) {
        privatePartyRepository.addGuest(login, pass, guestDto.getName(), guestDto.getEmail(), guestDto.getPhone());
    }

    public List<String> takeAllGuest(String login, String pass) {
        return privatePartyRepository.takeAllGuests(login, pass);
    }
}
