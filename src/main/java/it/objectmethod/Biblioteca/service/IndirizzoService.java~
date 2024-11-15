package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.IndirizzoDto;
import it.objectmethod.Biblioteca.entity.Indirizzo;
import it.objectmethod.Biblioteca.mapper.IndirizzoMapper;
import it.objectmethod.Biblioteca.repository.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private IndirizzoMapper indirizzoMapper;

    public List<IndirizzoDto> getAllIndirizzo() {
        return indirizzoMapper.indirizzoListToIndirizzoDtoList(indirizzoRepository.findAll());
    }

    public IndirizzoDto createIndirizzo(final IndirizzoDto indirizzoDto) {
        Indirizzo indirizzo = indirizzoMapper.indirizzoDtoToIndirizzo(indirizzoDto);
        return indirizzoMapper.indirizzoToIndirizzoDto(indirizzoRepository.save(indirizzo));
    }
}
