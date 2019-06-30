export interface JwtResponseVO {
    token: string;
    type: string;
    username: string;
    authorities: string[];
}
