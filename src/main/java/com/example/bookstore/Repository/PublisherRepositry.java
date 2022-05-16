package com.example.bookstore.Repository;

import com.example.bookstore.Dao.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PublisherRepositry extends JpaRepository<Publisher,Integer> {

    @Transactional
    @Modifying
    void updateIsaktive(Integer id);
}
