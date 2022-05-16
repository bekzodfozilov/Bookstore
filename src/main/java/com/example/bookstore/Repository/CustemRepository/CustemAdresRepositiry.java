package com.example.bookstore.Repository.CustemRepository;

import com.example.bookstore.Dao.Adres;
import com.example.bookstore.Dao.CustemEntitiy.CustemAdres;
import com.example.bookstore.Dao.CustemEntitiy.CustemAuthor;
import com.example.bookstore.Helper.IntegerHelper;
import com.example.bookstore.Helper.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustemAdresRepositiry {

    private final EntityManager entityManager;

    private Integer size;

    private Integer page;

    public Optional<?> getAllAdres(MultiValueMap<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder(" where 1 =1 and isaktive = true");

        if(IntegerHelper.BoolInt(params.getFirst("size")) && IntegerHelper.BoolInt(params.getFirst("page"))){
            stringBuilder.append(" limit :size offset :page");
        }

        queryparams(params,stringBuilder);

        String s = "select *  from address "  + stringBuilder;

        Query query = entityManager.createNativeQuery(s, CustemAdres.class);

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

    private void queryparams(MultiValueMap<String, String> params, StringBuilder stringBuilder) {
        if(IntegerHelper.BoolInt(params.getFirst("id"))){
            stringBuilder.append(" and id = :id");
        }
        if(IntegerHelper.BoolInt(params.getFirst("district_id"))){
            stringBuilder.append(" and distirct_id = : distirct_id");
        }
        if(IntegerHelper.BoolInt(params.getFirst("region_id"))){
            stringBuilder.append(" and region_id = : region_id");
        }
        if(IntegerHelper.BoolInt(params.getFirst("street"))){
            stringBuilder.append(" and street = : street");
        }
        
    }
    private void QueryValue(Query query, MultiValueMap<String, String> params, StringBuilder stringBuilder) {
        if(IntegerHelper.BoolInt(params.getFirst("id"))){
            query.setParameter("id",IntegerHelper.Isvalid(params.getFirst("id")));
        }
        if(IntegerHelper.BoolInt(params.getFirst("district_id"))){
            query.setParameter("district_id",IntegerHelper.Isvalid(params.getFirst("district_id")));
        }
        if(IntegerHelper.BoolInt(params.getFirst("region_id"))){
            query.setParameter("region_id",IntegerHelper.Isvalid(params.getFirst("region_id")));
        }
        if(StringHelper.Isvalid(params.getFirst("street"))){
            query.setParameter("street",params.getFirst("street"));
        }
    }
}
