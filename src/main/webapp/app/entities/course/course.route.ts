import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { CourseComponent } from './course.component';
import { CourseDetailComponent } from './course-detail.component';
import { CoursePopupComponent } from './course-dialog.component';
import { StudentsCoursePopupComponent } from './course-dialog-student.component';
import { CourseDeletePopupComponent } from './course-delete-dialog.component';

import { Principal } from '../../shared';

export const courseRoute: Routes = [
    {
        path: 'course',
        component: CourseComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cursos'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'course/:id',
        component: CourseDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cursos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const coursePopupRoute: Routes = [
    {
        path: 'course-new',
        component: CoursePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cursos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'course/:id/edit',
        component: CoursePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cursos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'course/:id/delete',
        component: CourseDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Cursos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: ':id/students',
        component: StudentsCoursePopupComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'Estudiantes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
