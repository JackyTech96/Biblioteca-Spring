package it.objectmethod.Biblioteca.pageable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponse<T> {
    private List<T> content;
    private int page;
    private int size;

    public PagedResponse(Page<T> page) {// TODO: costruttore per la creazione dato un T del mio Oggetto PagedResponse
        this.content = page.getContent();
        this.page = page.getNumber();
        this.size = page.getSize();
    }
}
