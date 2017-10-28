package com.app.service.mapper;

import com.app.domain.*;
import com.app.service.dto.StudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Student and its DTO StudentDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, })
public interface StudentMapper extends EntityMapper <StudentDTO, Student> {
    
    
    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
