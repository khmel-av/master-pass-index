import {ITypeFeedbackState} from "../../../reducers";
import {typeFeedbackTypes} from "../action/type.feedback.action";

const initialState: ITypeFeedbackState = {
    typeFeedbacks: []
};

export const typeFeedbackReducer = (state = initialState, action: any) => {
    switch (action.type) {
        case typeFeedbackTypes.GET_TYPE:
            return {
                ...state,
                typeFeedbacks: action.payload.typeFeedbacks
            };
        case typeFeedbackTypes.ERROR:
            return {
                ...state,
                typeFeedbackError: action.payload.typeFeedbackError
            };
    }
    return state;
};
