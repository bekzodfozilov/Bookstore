package com.example.bookstore.Repository.CustemRepository;
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
import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class CustemAuthorRepositry {


    private  final EntityManager entityManager;

    private Integer size;

    private Integer page;

    public Optional<?> getAllAuthor(MultiValueMap<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder(" where 1 = 1 and isaktive = true");

        if(IntegerHelper.BoolInt(params.getFirst("size")) && IntegerHelper.BoolInt(params.getFirst("page"))){
            stringBuilder.append(" limit :size offset :page");
        }

        queryparams(params,stringBuilder);

        String s = "Select a.id , a.birthdate , a.firstname || '' || a.lastname  as name  from author a " + stringBuilder;

        Query query = entityManager.createNativeQuery(s, CustemAuthor.class);

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

    private static void queryparams(MultiValueMap<String, String> params, StringBuilder stringBuilder) {
        if(IntegerHelper.BoolInt(params.getFirst("id"))){
            stringBuilder.append(" and a.id =: id");
        }
        if(StringHelper.Isvalid(params.getFirst("birthdate"))){
            stringBuilder.append(" and a.birthdate = :birthdate");
        }
        if(StringHelper.Isvalid(params.getFirst("firstname"))){
            stringBuilder.append(" and a.firstname = : firstname");
        }
        if(StringHelper.Isvalid(params.getFirst("lastname"))){
            stringBuilder.append(" and a.lastname = : lastname");
        }

    }
    private void QueryValue(Query query, MultiValueMap<String, String> params, StringBuilder stringBuilder) {
        if(IntegerHelper.BoolInt(params.getFirst("id"))){
           query.setParameter("id",IntegerHelper.Isvalid(params.getFirst("id")));
        }
        if(StringHelper.Isvalid(params.getFirst("birthdate"))){
            query.setParameter("birthdate",params.getFirst("birthdate"));
        }
        if(StringHelper.Isvalid(params.getFirst("firstname"))){
            query.setParameter("firstname",params.getFirst("firstname"));
        }
        if(StringHelper.Isvalid(params.getFirst("lastname"))){
            query.setParameter("lastname",params.getFirst("lastname"));
        }
    }

}
