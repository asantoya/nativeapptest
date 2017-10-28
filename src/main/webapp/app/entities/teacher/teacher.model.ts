import { BaseEntity } from './../../shared';

const enum Gender {
    'MALE',
    'FEMALE'
}

export class Teacher implements BaseEntity {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public identification?: string,
        public gender?: Gender,
        public courses?: BaseEntity[],
    ) {
    }
}
