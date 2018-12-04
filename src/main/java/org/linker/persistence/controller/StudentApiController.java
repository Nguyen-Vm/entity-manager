package org.linker.persistence.controller;

import org.linker.persistence.IPagination;
import org.linker.persistence.dto.request.StudentSearchRequest;
import org.linker.persistence.dto.response.StudentResponse;
import org.linker.persistence.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author RWM
 * @date 2018/11/28
 */
@RestController
@RequestMapping("/api/student")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    @PostMapping("//search")
    public IPagination<StudentResponse> search(@RequestBody StudentSearchRequest request) {
        return studentService.search(request);
    }

}
