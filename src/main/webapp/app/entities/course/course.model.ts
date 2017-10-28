import { BaseEntity } from './../../shared';

export class Course implements BaseEntity {
    constructor(
        public id?: number,
        public code?: string,
        public name?: string,
        public observations?: string,
        public students?: BaseEntity[],
        public teachers?: BaseEntity[],
    ) {
    }
}
