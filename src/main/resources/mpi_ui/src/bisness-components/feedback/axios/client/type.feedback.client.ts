import { AxiosPromise } from 'axios';
import { client } from "../../../../axios/client";
import {ITypeFeedback} from "../../model/type.feedback.modal";

const typeFeedbackContext = '/feedback'
const urls = {
    getTypes: typeFeedbackContext + '/public/type/list',
};

export const typeFeedbackClient = {
    getTypes(): AxiosPromise<ITypeFeedback> {
        return client.get(urls.getTypes);
    }
}