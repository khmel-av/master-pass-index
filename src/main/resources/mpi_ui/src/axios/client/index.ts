import axios from 'axios';
import { environment } from '../../environment';

export const client = axios.create({
  baseURL: environment.originContext,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json'
  }
});
