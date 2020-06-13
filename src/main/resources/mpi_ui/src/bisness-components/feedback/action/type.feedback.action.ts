import {typeFeedbackClient} from "../axios/client/type.feedback.client";

export const typeFeedbackTypes = {
    GET_TYPE: '[TYPE_FEEDBACK] GET_TYPE',
    ERROR: '[TYPE_FEEDBACK] ERROR'
};

export const getTypes = () => async (dispatch: any) => {
    try {
        const response = await typeFeedbackClient.getTypes();
        dispatch({
            payload: {
                typeFeedbacks: response.data
            },
            type: typeFeedbackTypes.GET_TYPE
        })
    } catch (err) {
        console.log('error getting list type feedback');
        dispatch({
            payload: {
                typeFeedbackError: err.data
            },
            type: typeFeedbackTypes.ERROR
        })
    }
};