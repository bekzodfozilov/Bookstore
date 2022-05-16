package com.example.bookstore.Repository.CustemRepository;
import com.example.bookstore.Dao.CustemEntitiy.CustemBook;
import com.example.bookstore.Helper.IntegerHelper;
import com.example.bookstore.Helper.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustemBookRepository {

     private final EntityManager entityManager;

     private Integer size;

     private Integer page;

    public Optional<?> getAllBooks(MultiValueMap<String,String> params){
       StringBuilder stringBuilder = new StringBuilder(" where 1=1 and t.isaktive = true ");

        if(IntegerHelper.BoolInt(params.getFirst("size")) && IntegerHelper.BoolInt(params.getFirst("page"))){
            stringBuilder.append(" limit :size offset :page");
        }

       queryparams(params,stringBuilder);

        String s = "Select t.id  , t.nameuz , a.firstname || ' '  || a.lastname  as author, p.name as publisher , cost  from book t " +
                "left join author a on t.author_id = a.id " +
                "left join publisher p on t.publisher_id = p.id  " + stringBuilder;

        Query query = entityManager.createNativeQuery(s, CustemBook.class);

        if (IntegerHelper.BoolInt(params.getFirst("size"))) {
            query.setParameter("size", IntegerHelper.Isvalid(params.getFirst("size")));
        }
        if (IntegerHelper.BoolInt(params.getFirst("page"))) {
            size = IntegerHelper.Isvalid(params.getFirst("size"));
            page = IntegerHelper.Isvalid(params.getFirst("page"));
            query.setParameter("page", size * page);
        }

       QueryValue( query, params,stringBuilder);

       if(params.containsKey("size") && params.containsKey("page")){
          if(IntegerHelper.BoolInt(params.getFirst("size")) && IntegerHelper.BoolInt(params.getFirst("page"))){
            return Optional.of(
             new PageImpl<>(query.getResultList(), PageRequest.of(page,size),query.getResultList().size())
            );
          }
       }
       return Optional.of(query.getResultList());


    }


    public void queryparams(MultiValueMap<String,String> params,StringBuilder stringBuilder){
       if(IntegerHelper.BoolInt(params.getFirst("id"))){
           stringBuilder.append(" And t.id = :id");
       }
       if(StringHelper.Isvalid(params.getFirst("nameuz"))){
           stringBuilder.append(" And t.nameuz = :nameuz");
       }
       if(IntegerHelper.BoolInt(params.getFirst("cost"))){
           stringBuilder.append(" And t.cost = :cost");
       }
       if(StringHelper.Isvalid(params.getFirst("published_date"))){
           stringBuilder.append(" And t.published_date = : published_date");
       }
       if(IntegerHelper.BoolInt(params.getFirst("page_count"))){
           stringBuilder.append(" And t.page_count = :page_count");
       }
       if(IntegerHelper.BoolInt(params.getFirst("author_id"))) {
           stringBuilder.append(" And t.author_id = :author_id");
       }
       if(IntegerHelper.BoolInt(params.getFirst("publisher_id"))){
           stringBuilder.append(" And t.publisher_id = :publisher_id");
       }
       if(StringHelper.Isvalid(params.getFirst("genre"))){
           stringBuilder.append(" And t.genre = genre");
       }

    }

    private void QueryValue(Query query, MultiValueMap<String, String> params, StringBuilder stringBuilder) {
      if(IntegerHelper.BoolInt(params.getFirst("id"))){
          query.setParameter("id",IntegerHelper.Isvalid(params.getFirst("id")));
      }
        if(StringHelper.Isvalid(params.getFirst("nameuz"))){
           query.setParameter("nameuz",params.getFirst("nameuz"));
        }
        if(IntegerHelper.BoolInt(params.getFirst("cost"))){
            query.setParameter("cost",IntegerHelper.Isvalid(params.getFirst("csot")));
        }
        if(StringHelper.Isvalid(params.getFirst("published_date"))){
            query.setParameter("published_date",params.getFirst("published_date"));
        }
        if(IntegerHelper.BoolInt(params.getFirst("page_count"))){
            query.setParameter("page_count",IntegerHelper.Isvalid(params.getFirst("page_cost")));
        }
        if(IntegerHelper.BoolInt(params.getFirst("author_id"))) {
            query.setParameter("author_id",IntegerHelper.Isvalid(params.getFirst("author_id")));
        }
        if(IntegerHelper.BoolInt(params.getFirst("publisher_id"))){
            query.setParameter("publisher_id",IntegerHelper.Isvalid(params.getFirst("publisher_id")));
        }
        if(StringHelper.Isvalid(params.getFirst("genre"))){
            query.setParameter("genre",params.getFirst("genre"));
        }



    }

}
