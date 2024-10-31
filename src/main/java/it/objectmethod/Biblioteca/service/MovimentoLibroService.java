package it.objectmethod.Biblioteca.service;

import it.objectmethod.Biblioteca.dto.MovimentoLibroDto;
import it.objectmethod.Biblioteca.entity.MovimentoLibro;
import it.objectmethod.Biblioteca.mapper.MovimentoLibroMapper;
import it.objectmethod.Biblioteca.repository.MovimentoLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentoLibroService {
    @Autowired
    private MovimentoLibroRepository movimentoLibroRepository;
    @Autowired
    private MovimentoLibroMapper movimentoLibroMapper;

    public List<MovimentoLibroDto> getAllMovimentoLibro() {
        return movimentoLibroMapper.movimentoLibroListToMovimentoLibroDtoList(movimentoLibroRepository.findAll());
    }

    public MovimentoLibroDto createMovimentoLibro(MovimentoLibroDto movimentoLibroDto) {
        MovimentoLibro movimentoLibro = movimentoLibroMapper.movimentoLibroDtoToMovimentoLibro(movimentoLibroDto);
        return movimentoLibroMapper.movimentoLibroToMovimentoLibroDto(movimentoLibroRepository.save(movimentoLibro));
    }
}
