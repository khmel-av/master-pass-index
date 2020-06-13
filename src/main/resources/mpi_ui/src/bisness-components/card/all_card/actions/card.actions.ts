import {IPassCard} from "../model/card.model";
import {cardClient} from "../axios/client/card.client";

export const cardTypes = {
    CARD_LIST: '[CARD] CARD_LIST',
    CARD_BY_NUMBER: '[CARD] CARD_BY_NUMBER',
    FILTER_CARDS: '[CARD] FILTER_CARDS',
    DELETE_CARD: '[CARD] DELETE_CARD',
    UPDATE_CARD: '[CARD] UPDATE_CARD',
    SAVE_CARD: '[CARD] SAVE_CARD'
};

export const cardList = (userId:number) => async (dispatch: any) => {
    try {
        const response = await cardClient.cardList(userId);
        dispatch({
            payload: {
                cards: response.data
            },
            type: cardTypes.CARD_LIST
        })
    } catch (err) {
        console.log('error getting list def cards');
        dispatch({
            payload: {},
            type: ''
        })
    }
};

export const updateCardByNumber = (cardByNumber: IPassCard) => {
    return {
        payload: {
            cardByNumber
        },
        type: cardTypes.CARD_BY_NUMBER
    }
};

export const getCardByNumber = (number: string) => async (dispatch: any) => {
    try {
        const response = await cardClient.getCardByNumber(number);
        dispatch({
            payload: {
                cardByNumber: response.data
            },
            type: cardTypes.CARD_BY_NUMBER
        })
    } catch (err) {
        console.log('error getting card by number');
        dispatch({
            payload: {
            },
            type: ''
        })
    }
};

export const filterCards = (id: number) => async (dispatch: any) => {
    try {
        const response = await cardClient.filterCards(id);
        dispatch({
            payload: {
                cards: response.data
            },
            type: cardTypes.FILTER_CARDS
        })
    } catch (err) {
        console.log('error getting list cards by filter');
        dispatch({
            payload: {},
            type: ''
        })
    }
};

export const deleteCardById = (id: number) => async (dispatch: any) => {
    try {
        const response = await cardClient.deleteCardById(id);
        dispatch({
            payload: {
                answer: response.data
            },
            type: cardTypes.DELETE_CARD
        })
    } catch (err) {
        console.log('error delete the card by id');
        dispatch({
            payload: {},
            type: ''
        })
    }
};

export const updateCard = (card: IPassCard) => async (dispatch: any) => {
    try {
        const response = await cardClient.updateCard(card);
        dispatch({
            payload: {
                answer: response.data
            },
            type: cardTypes.UPDATE_CARD
        })
    } catch (err) {
        console.log('error update the card');
        dispatch({
            payload: {},
            type: ''
        })
    }
};

export const saveCard = (card: IPassCard) => async (dispatch: any) => {
    try {
        const response = await cardClient.saveCard(card);
        dispatch({
            payload: {
                answer: response.data
            },
            type: cardTypes.SAVE_CARD
        })
    } catch (err) {
        console.log('error save the card');
        dispatch({
            payload: {},
            type: ''
        })
    }
};