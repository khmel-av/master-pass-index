import {IFeedback} from "../model/feedback.modal";
import {feedbackClient} from "../axios/client/feedback.client";

export const feedbackTypes = {
    SEND_MESSAGE: '[FEEDBACK] SEND_MESSAGE',
    ERROR: '[FEEDBACK] ERROR'
};

export const sendMessage = (feedback:IFeedback) => async (dispatch: any) => {
    try {
        await feedbackClient.sendMessage(feedback);
        dispatch({
            payload: {},
            type: ''
        })
    } catch (err) {
        console.log('error send message');
        dispatch({
            payload: {
                error: err.data
            },
            type: feedbackTypes.ERROR
        })
    }
};