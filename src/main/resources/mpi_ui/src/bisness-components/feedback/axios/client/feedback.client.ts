import { AxiosPromise } from 'axios';
import { client } from "../../../../axios/client";
import {IFeedback} from "../../model/feedback.modal";

const feedbackContext = '/feedback'
const urls = {
    sendMessage: feedbackContext + '/public/send',
};

export const feedbackClient = {
    sendMessage(feedback: IFeedback): AxiosPromise<void> {
        return client.post(urls.sendMessage, feedback);
    }
}