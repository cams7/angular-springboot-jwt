import { ErrorDetails } from './error-details';

export interface ValidationErrorDetails extends ErrorDetails {
    fields: ValidationErrorField[];
}
export interface ValidationErrorField {
    name: string;
    message: string;
}
