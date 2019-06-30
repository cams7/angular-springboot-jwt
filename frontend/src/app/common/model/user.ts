import { Role } from './role';

export interface User {
    username: string;
    roles: Role[];
}
