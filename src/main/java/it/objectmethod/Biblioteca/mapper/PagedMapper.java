package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.pageable.PagedResponse;
import org.mapstruct.Mapping;

import java.util.List;

public interface PagedMapper <T> {
    //    default <T, R> PagedResponse<R> pageToPagedResponse(Page<T> page, Function<T, R> mapperFunction) {
//        PagedResponse<R> response = new PagedResponse<>();
//        response.setContent(page.map(mapperFunction).getContent());
//        response.setPage(page.getPageable().getPageNumber());
//        response.setSize(page.getSize());
//        return response;
//    }

    @Mapping(target = "content", source = "content")
    @Mapping(target = "page", source = "page")
    @Mapping(target = "size", source = "size")
    PagedResponse<T> toPagedResponse(List<T> content, int page, int size);
}
