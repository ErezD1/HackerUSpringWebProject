package edu.erezd.erezproject.service;

import edu.erezd.erezproject.dto.TicketCloseDTO;
import edu.erezd.erezproject.dto.TicketCreateDTO;
import edu.erezd.erezproject.dto.TicketResponseDTO;

import java.util.List;

public interface TicketService {

    TicketResponseDTO createTicket(TicketCreateDTO ticketDTO);

    TicketResponseDTO getTicketById(long id);

    TicketResponseDTO updateTicket(long id, TicketCreateDTO ticketDTO);

    TicketResponseDTO closeTicket(long id, TicketCloseDTO ticketDTO);

    List<TicketResponseDTO> getAllTickets();
}
