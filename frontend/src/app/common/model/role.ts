import { User } from './user';

export interface Role {
    id: number;
    name: RoleName;
    users: User[];
}
export enum RoleName {
    ROLE_USER = "ROLE_USER", 
    ROLE_PM = "ROLE_PM", 
    ROLE_ADMIN = "ROLE_ADMIN"
}
