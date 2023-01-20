package org.locadora.utils;

import java.util.List;
import java.util.stream.Collectors;

public class Pagination {
    public static <T> List<T> exec(List<T> list, int pageSize, int pageNumber) {
        List<T> pagination;

        pagination = list.stream()
                .skip((pageNumber) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        return pagination;
    }
}
