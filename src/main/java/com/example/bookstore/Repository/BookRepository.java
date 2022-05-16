package com.example.bookstore.Repository;

import com.example.bookstore.Dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update  book set isaktive = false where id = :id and isaktive = true ",nativeQuery = true)
    void uptade(@Param("id") Integer id);

}
