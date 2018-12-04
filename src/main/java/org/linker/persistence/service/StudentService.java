package org.linker.persistence.service;

import org.linker.persistence.IPagination;
import org.linker.persistence.dto.request.StudentSearchRequest;
import org.linker.persistence.dto.response.StudentResponse;
import org.linker.persistence.mapper.PaginationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RWM
 * @date 2018/12/4
 */
@Service
public class StudentService {

    @Autowired
    private PaginationMapper paginationMapper;

    /**
     * 分页查询学生信息
     * @param request
     * @return
     */
    public IPagination<StudentResponse> search(StudentSearchRequest request) {
        // 拼接SQL语句
        StringBuilder sql = new StringBuilder("SELECT id, name FROM t_galidun_student ");
        // 查询需要的参数，先存进Map
        Map<String, Object> maps = new HashMap<>();
        if (request.name != null) {
            sql.append("WHERE name LIKE :name");
            maps.put("name", "%" + request.name + "%");
        }
        // 调用通用方法返回查询结果
        return paginationMapper.search(request.nowPage, request.pageSize, sql.toString(), maps, StudentResponse.class);
    }
}
