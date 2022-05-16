package com.example.bookstore.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Data
@Entity
@Table(name = "publisher")
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "Publisher.updateIsaktive",query = "update Publisher set isaktive = false where id = ?1 and isaktive = true ")
public class Publisher {

    @Id
    @GeneratedValue(generator = "publisher_id_seq")
    @SequenceGenerator(name = "publisher_id_seq",sequenceName = "publisher_id_seq",allocationSize = 1)
    public Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adres_id",referencedColumnName = "id")
    private Adres adres;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "publisher")
    private List<Book> books;

    @Column(name = "isaktive")
    private boolean isaktive;

    public Publisher(Integer id, String name, boolean isaktive) {
        this.id = id;
        this.name = name;
        this.isaktive = isaktive;
    }
}
