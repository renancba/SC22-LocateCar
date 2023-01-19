package org.locadora.utils;

import java.util.List;
import java.util.stream.Collectors;

public class Pagination {
    public static <T> List<T> exec(List<T> list, int pageSize, int pageNumber) {
        List<T> pagination;

        if (pageNumber < 0) pageNumber = 0;
        if (pageSize < 0) pageSize = 0;
        if (pageNumber + pageSize > list.size()) pageNumber = list.size() - pageSize;
        if (pageNumber < 0 || pageNumber >= list.size()) pageNumber = 0;

        pagination = list.stream()
                .skip((pageNumber) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        return pagination;
    }
}
