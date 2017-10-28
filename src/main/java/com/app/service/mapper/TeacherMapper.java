package com.app.service.mapper;

import com.app.domain.*;
import com.app.service.dto.TeacherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Teacher and its DTO TeacherDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, })
public interface TeacherMapper extends EntityMapper <TeacherDTO, Teacher> {
    
    
    default Teacher fromId(Long id) {
        if (id == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
