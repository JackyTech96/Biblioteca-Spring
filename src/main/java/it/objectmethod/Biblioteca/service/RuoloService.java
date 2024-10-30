package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.RuoloDto;
import it.objectmethod.Biblioteca.mapper.RuoloMapper;
import it.objectmethod.Biblioteca.repository.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuoloService {
    @Autowired
    private RuoloRepository ruoloRepository;

    @Autowired
    private RuoloMapper ruoloMapper;

    public List<RuoloDto> getAllRuolo() {
        return ruoloMapper.ruoloListToRuoloDtoList(ruoloRepository.findAll());
    }
}


