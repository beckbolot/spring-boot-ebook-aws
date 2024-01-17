package com.beck.springbootebookaws.repository;

import com.beck.springbootebookaws.db.entity.Book;
import com.beck.springbootebookaws.db.enums.RequestStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("select b from Book b where UPPER(b.author) like concat('%',:name,'%') " +
    "or upper(b.publishingHouse) like concat('%',:name,'%')" +
    "or upper(b.title) like concat('%',:name,'%')" +
    "or upper(b.genreEnum) like concat('%',:name,'%')" +
    "or upper(b.typeOfBook) like concat('%',:name,'%')" +
    "or upper(b.bookLanguage) like concat('%',:name,'%')")
    List<Book> searchAndPagination(@Param("name") String name, Pageable pageable);

    List<Book> findAllByRequestStatus(RequestStatus requestStatus,Pageable pageable);

    List<Book> findAllByRequestStatus(RequestStatus requestStatus);

    List<Book> findAll(Specification<Book> specification,Pageable pageable);


}
