import { AxiosPromise } from 'axios';
import {client} from "../../../../../axios/client";
import {IPassCard} from "../../model/card.model";

const cardContext = '/pass/card'
const urls = {
    cardList: cardContext + '/list',
    getCardByNumber: cardContext,
    filterCards: cardContext + '/list',
    deleteCardById: cardContext + '/delete',
    updateCard: cardContext + '/update',
    saveCard: cardContext + '/add'
};

export const cardClient = {
    cardList(userId:number): AxiosPromise<IPassCard[]> {
        return client.get(urls.cardList + `/${userId}`);
    },
    getCardByNumber(number: string): AxiosPromise<IPassCard> {
        return client.get(urls.getCardByNumber + `/${number}`);
    },
    filterCards(id: number): AxiosPromise<IPassCard[]> {
        return client.post(urls.filterCards, id);
    },
    deleteCardById(id: number) {
        return client.get(urls.deleteCardById + `/${id}`);
    },
    updateCard(card: IPassCard) {
        return client.post(urls.updateCard, card);
    },
    saveCard(card: IPassCard) {
        return client.post(urls.saveCard, card);
    }
}