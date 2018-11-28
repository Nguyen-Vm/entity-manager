package org.linker.persistence.mapper;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.linker.persistence.IPagination;
import org.linker.persistence.common.DtProcessHelper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;

/**
 * @author RWM
 * @date 2018/11/28
 */
@Component
public class PaginationMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public <T> IPagination<T> search(Integer nowPage, Integer pageSize, String sql, Map<String, Object> maps, Class<T> clazz) {
        IPagination pagination = IPagination.create(nowPage, pageSize);
        pagination.setTotal(DtProcessHelper.setParameters(entityManager.createNativeQuery(sql), maps).getResultList().size());
        Query query = DtProcessHelper.setParameters(entityManager.createNativeQuery(sql), maps);
        query.setFirstResult(pagination.getOffset()).setMaxResults(pagination.getSize());
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(clazz));
        pagination.setList(query.getResultList());
        return pagination;
    }
}
