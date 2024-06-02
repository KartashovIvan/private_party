package org.javaacademy.private_party.controller;

import lombok.AllArgsConstructor;
import org.javaacademy.private_party.dto.GuestDto;
import org.javaacademy.private_party.service.PrivatePartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
public class PrivatePartyController {
    private PrivatePartyService privatePartyService;

    @PostMapping("/add-guest")
    public void addGuest(@RequestHeader String login,
                         @RequestHeader String pass,
                         @RequestBody GuestDto guestDto) {
        privatePartyService.addGuest(login, pass, guestDto);
    }

    @GetMapping("/all-guests")
    public ResponseEntity<List<String>> takeAllGuest(@RequestHeader String login,
                                             @RequestHeader String pass) {
        return ResponseEntity.ok(privatePartyService.takeAllGuest(login, pass));
    }
}
