package edu.erezd.erezproject.controller;

import edu.erezd.erezproject.dto.TicketCloseDTO;
import edu.erezd.erezproject.dto.TicketCreateDTO;
import edu.erezd.erezproject.dto.TicketResponseDTO;
import edu.erezd.erezproject.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> getTickets(@RequestBody @Valid TicketCreateDTO dto,
                                                        UriComponentsBuilder uriBuilder)
    {
        var res = ticketService.createTicket(dto);
        var uri = uriBuilder.path("/api/v1/tickets/{id}").buildAndExpand(res.getId()).toUri();

        return ResponseEntity.created(uri).body(res);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable long id) {

        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @PathVariable long id,
            @RequestBody @Valid TicketCreateDTO dto)
    {
        return ResponseEntity.ok(ticketService.updateTicket(id, dto));
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<TicketResponseDTO> closeTicket(
            @PathVariable long id,
            @RequestBody @Valid TicketCloseDTO dto
    )

    {
        return ResponseEntity.ok(ticketService.closeTicket(id, dto));
    }

    @GetMapping
    public ResponseEntity<TicketResponseDTO> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
