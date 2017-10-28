import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Course } from './course.model';
import { CoursePopupService } from './course-popup.service';
import { CourseService } from './course.service';
import { Teacher, TeacherService } from '../teacher';
import { ResponseWrapper } from '../../shared';

import { Student, StudentService } from '../student';

@Component({
    selector: 'jhi-course-dialog-student',
    templateUrl: './course-dialog-student.component.html'
})
export class StudentCourseDialogComponent implements OnInit {
  routeSub: any;

    authorities: any[];
    isSaving: boolean;
    students: Student[];
    course: Course;

  constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private studentService: StudentService,
        private eventManager: JhiEventManager,
        private courseService: CourseService,
        private route: ActivatedRoute
    ) {
    }
  loadAll() {
        this.studentService.query().subscribe(
            (res: ResponseWrapper) => {
                this.students = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

  addStudent() {
    this.routeSub = this.route.params.subscribe((params) => {
      this.courseService.addStudentToCourse(this.course, params['student_id']);
    });

  }

  ngOnInit() {
        console.log('curso actual ' + this.course.id);
        this.loadAll();
      console.log(this.students);
    }

   clear() {
        this.activeModal.dismiss('cancel');
    }

  private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}

@Component({
    selector: 'jhi-students-popup',
    template: ''
})
export class StudentsCoursePopupComponent implements OnInit, OnDestroy {
   modalRef: NgbModalRef;
    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private coursePopupService: CoursePopupService
    ) {}

  ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.modalRef = this.coursePopupService
                    .open(StudentCourseDialogComponent, params['id']);
            }
        });
    }
      ngOnDestroy() {
        this.routeSub.unsubscribe();
    }

}
