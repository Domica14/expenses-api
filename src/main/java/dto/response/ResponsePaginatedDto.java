package dto.response;

import java.util.List;

public class ResponsePaginatedDto<T> {
    public List<T> data;
    public int page;
    public int size;
    public long totalElements;
    public int totalPages;

    public ResponsePaginatedDto(List<T> data, int page, int size, long totalElements, int totalPages) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
