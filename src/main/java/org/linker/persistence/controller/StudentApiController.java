package org.linker.persistence.controller;

import org.linker.persistence.IPagination;
import org.linker.persistence.dto.request.StudentSearchRequest;
import org.linker.persistence.dto.response.StudentResponse;
import org.linker.persistence.mapper.PaginationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RWM
 * @date 2018/11/28
 */
@RestController
public class StudentApiController {


    @Autowired
    private PaginationMapper paginationMapper;

    @PostMapping("/search")
    public IPagination<StudentResponse> search(@RequestBody StudentSearchRequest request) {
        StringBuilder sql = new StringBuilder("SELECT * FROM t_galidun_student ");
        Map<String, Object> maps = new HashMap<>();
        if (request.name != null) {
            sql.append("WHERE name LIKE :name");
            maps.put("name", "%" + request.name + "%");
        }
        return paginationMapper.search(request.nowPage, request.pageSize, sql.toString(), maps, StudentResponse.class);
    }
}
