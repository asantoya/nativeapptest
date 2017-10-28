import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ApptestSharedModule } from '../../shared';
import {
    TeacherService,
    TeacherPopupService,
    TeacherComponent,
    TeacherDetailComponent,
    TeacherDialogComponent,
    TeacherPopupComponent,
    TeacherDeletePopupComponent,
    TeacherDeleteDialogComponent,
    teacherRoute,
    teacherPopupRoute,
} from './';

const ENTITY_STATES = [
    ...teacherRoute,
    ...teacherPopupRoute,
];

@NgModule({
    imports: [
        ApptestSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        TeacherComponent,
        TeacherDetailComponent,
        TeacherDialogComponent,
        TeacherDeleteDialogComponent,
        TeacherPopupComponent,
        TeacherDeletePopupComponent,
    ],
    entryComponents: [
        TeacherComponent,
        TeacherDialogComponent,
        TeacherPopupComponent,
        TeacherDeleteDialogComponent,
        TeacherDeletePopupComponent,
    ],
    providers: [
        TeacherService,
        TeacherPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ApptestTeacherModule {}
