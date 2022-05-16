package com.example.bookstore.Repository;

import com.example.bookstore.Dao.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AdresReposity extends JpaRepository<Adres,Integer> {
    @Transactional
    @Modifying
    @Query(name = "Adres.updateIsaktive",value = "update Adres set isaktive = false where id = :id and isaktive = true ")
    void updateIsaktive(@Param("id") Integer id);
}
