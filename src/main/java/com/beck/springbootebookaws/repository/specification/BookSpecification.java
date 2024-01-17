package com.beck.springbootebookaws.repository.specification;

import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.enums.Genre;
import com.beck.springbootebookaws.db.enums.RequestStatus;
import com.beck.springbootebookaws.db.enums.TypeOfBook;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookSpecification {

    public static Specification<Book> getFilter(Genre genreEnum, TypeOfBook typeOfBook) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (genreEnum != null) {
                predicates.add(criteriaBuilder.equal(root.get("genre"), genreEnum));
            }
            if (typeOfBook != null) {
                predicates.add(criteriaBuilder.equal(root.get("typeOfBook"), typeOfBook));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    public static Specification<Book> getByStatusAndTypeOfBook(Genre genre, TypeOfBook type, RequestStatus status) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (genre != null) {
                predicates.add(criteriaBuilder.equal(root.get("genre"), genre));
            }
            if (type != null) {
                predicates.add(criteriaBuilder.equal(root.get("typeOfBook"), type));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
