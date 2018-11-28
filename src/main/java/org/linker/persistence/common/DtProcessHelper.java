package org.linker.persistence.common;

import javax.persistence.Query;
import java.util.Map;

/**
 * @author RWM
 * @date 2018/11/28
 */
public class DtProcessHelper {

    public static Query setParameters(Query query, Map<String, Object> maps) {
        if (maps.size() > 0) {
            for (String key : maps.keySet()) {
                query.setParameter(key, maps.get(key));
            }
        }
        return query;
    }
}
