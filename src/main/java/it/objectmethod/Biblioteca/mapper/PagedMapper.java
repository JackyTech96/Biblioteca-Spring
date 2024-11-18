package it.objectmethod.Biblioteca.mapper;

import it.objectmethod.Biblioteca.dto.UtenteDto;
import it.objectmethod.Biblioteca.pageable.PagedResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface PagedMapper {
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
    PagedResponse<UtenteDto> toPagedResponse(List<UtenteDto> content, int page, int size);
}
