import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ApptestCourseModule } from './course/course.module';
import { ApptestStudentModule } from './student/student.module';
import { ApptestTeacherModule } from './teacher/teacher.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        ApptestCourseModule,
        ApptestStudentModule,
        ApptestTeacherModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ApptestEntityModule {}
