package edu.erezd.erezproject.service.impl;

import edu.erezd.erezproject.dto.TicketCloseDTO;
import edu.erezd.erezproject.dto.TicketCreateDTO;
import edu.erezd.erezproject.dto.TicketResponseDTO;
import edu.erezd.erezproject.entity.Status;
import edu.erezd.erezproject.entity.Ticket;
import edu.erezd.erezproject.entity.User;
import edu.erezd.erezproject.repository.TicketRepository;
import edu.erezd.erezproject.repository.UserRepository;
import edu.erezd.erezproject.service.TicketService;
import edu.erezd.erezproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public TicketResponseDTO createTicket(TicketCreateDTO ticketDTO) {
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        ticket.setStatus(Status.OPEN); // Set status on the entity
        ticket.setCreatedAt(LocalDateTime.now()); // Set createdAt on the entity

        // Use userService to get the user entity
        User user = userService.getUserEntityOrThrow(ticketDTO.getUser().getId());
        ticket.setUser(user); // Set the user on the ticket

        Ticket saved = ticketRepository.save(ticket);

        return modelMapper.map(saved, TicketResponseDTO.class);
    }



    @Override
    public TicketResponseDTO getTicketById(long id) {
    Ticket ticket = getTicketEntityOrThrow(id);

    return modelMapper.map(ticket, TicketResponseDTO.class);
    }

    private Ticket getTicketEntityOrThrow(long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public TicketResponseDTO updateTicket(long id, TicketCreateDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        modelMapper.typeMap(TicketCreateDTO.class, Ticket.class).addMappings(mapper -> {
            mapper.skip(Ticket::setCreatedAt); // Skip mapping for createdAt field
        });
        ticket.setStatus(Status.IN_PROGRESS); // Update status
        ticket.setUpdatedAt(LocalDateTime.now()); // Update updatedAt
        Ticket saved = ticketRepository.save(ticket);

        return modelMapper.map(saved, TicketResponseDTO.class);
    }


    @Override
    public TicketResponseDTO closeTicket(long id, TicketCloseDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (ticket.getStatus() == Status.CLOSED) {
            String closedAt = ticket.getUpdatedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            throw new IllegalStateException(
                    "Ticket is already closed as of " + closedAt + ". Closing comment was: '" + ticket.getClosingComment() + "'");
        }

        ticket.setStatus(Status.CLOSED); // Close the ticket
        ticket.setClosingComment(ticketDTO.getClosingComment()); // Set the closing comment
        ticket.setUpdatedAt(LocalDateTime.now()); // Update updatedAt
        Ticket saved = ticketRepository.save(ticket);

        return modelMapper.map(saved, TicketResponseDTO.class);
    }


    @Override
    public List<TicketResponseDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(ticket -> modelMapper.map(ticket, TicketResponseDTO.class))
                .collect(Collectors.toList());
    }

}
