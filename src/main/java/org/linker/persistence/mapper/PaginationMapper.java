package org.linker.persistence.mapper;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.linker.persistence.IPagination;
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

    /**
     * 分页查询通用方法
     *
     * @param nowPage  当前页
     * @param pageSize 每页条数
     * @param sql      sql语句
     * @param maps     sql查询参数
     * @param clazz    返回类型
     * @param <T>
     * @return
     */
    public <T> IPagination<T> search(Integer nowPage, Integer pageSize, String sql, Map<String, Object> maps, Class<T> clazz) {
        // 初始化分页返回体
        IPagination pagination = IPagination.create(nowPage, pageSize);
        // 查询结果总条数
        int total = getQueryWithParameters(entityManager.createNativeQuery(sql), maps).getResultList().size();
        pagination.setTotal(total);
        if (total == 0) return pagination;
        Query query = getQueryWithParameters(entityManager.createNativeQuery(sql), maps);
        // 忽略指定条数据，返回一页数据
        query.setFirstResult(pagination.getOffset()).setMaxResults(pagination.getSize());
        // 指定返回对象类型
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.aliasToBean(clazz));
        // 列表数据
        pagination.setList(query.getResultList());
        return pagination;
    }

    /**
     * 设置查询所需的参数
     *
     * @param query
     * @param maps
     * @return
     */
    private Query getQueryWithParameters(Query query, Map<String, Object> maps) {
        if (maps.size() > 0) {
            for (String key : maps.keySet()) {
                query.setParameter(key, maps.get(key));
            }
        }
        return query;
    }
}
