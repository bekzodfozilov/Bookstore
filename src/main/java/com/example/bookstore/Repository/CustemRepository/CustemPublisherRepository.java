package com.example.bookstore.Repository.CustemRepository;
import com.example.bookstore.Dao.CustemEntitiy.CustemPublisher;
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
public class CustemPublisherRepository {


    private final EntityManager entityManager;

    private Integer size;

    private Integer page;

    public Optional<?> getAllPublisher(MultiValueMap<String,String> params){

        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1 and isaktive = true");

        if(IntegerHelper.BoolInt(params.getFirst("size")) && IntegerHelper.BoolInt(params.getFirst("page"))){
            stringBuilder.append(" limit :size offset :page");
        }

        queryparams(params,stringBuilder);

        String s = " select * from publisher  " + stringBuilder;

        Query query = entityManager.createNativeQuery(s, CustemPublisher.class);

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
        if(StringHelper.Isvalid(params.getFirst("name"))){
            stringBuilder.append(" and name = : name");
        }
        if(IntegerHelper.BoolInt(params.getFirst("adres_id"))){
            stringBuilder.append(" and adres_id = : adres_id");
        }
    }

    private void QueryValue(Query query, MultiValueMap<String, String> params, StringBuilder stringBuilder) {
        if(IntegerHelper.BoolInt(params.getFirst("id"))){
            query.setParameter("id",IntegerHelper.Isvalid(params.getFirst("id")));
        }
        if(StringHelper.Isvalid(params.getFirst("name"))){
            query.setParameter("name",params.getFirst("name"));
        }
        if(IntegerHelper.BoolInt(params.getFirst("adres_id"))){
            query.setParameter("adres_id",IntegerHelper.Isvalid(params.getFirst("adres_id")));
        }
    }


}
