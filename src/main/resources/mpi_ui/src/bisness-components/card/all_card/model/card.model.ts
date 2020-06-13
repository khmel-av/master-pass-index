import {IStatusCard} from "./status.model";
import {IReasonCard} from "./reason.model";
import {ITypeCard} from "./type.model";
import {IScopeCard} from "./scope.model";
import {IUser} from "../../../user/model/user.model";

export interface IPassCard {
  id: number;
  number: string;
  reasons: IReasonCard[];
  scopes: IScopeCard[];
  types: ITypeCard[];
  status: IStatusCard;
  startDate: string;
  expirationDate: string;
  user: IUser;
  createDate: string;
  description?: string;
}