package com.example.bookstore.Repository;

import com.example.bookstore.Dao.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AuthorRepositry extends JpaRepository<Author , Integer> {

    @Transactional
    @Modifying
    @Query(value = "update author set isaktive = false where id = :id and isaktive = true",nativeQuery = true)
    void UpdateIsaktive(@Param("id") Integer id);
}
