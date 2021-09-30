import {
  COURIER_ADD_FAIL,
  COURIER_ADD_REQUEST,
  COURIER_ADD_SUCCESS,
  COURIER_FETCH_FAIL,
  COURIER_FETCH_REQUEST,
  COURIER_FETCH_SUCCESS,
} from '../constants/courierConstants'
import axios from 'axios'

export const addCourier = (title, contents) => {
  return (dispatch) => {
    dispatch({
      type: COURIER_ADD_REQUEST,
    })

    const header = {
      headers: {
        'Content-Type': 'application/json',
        token: sessionStorage['token'],
      },
    }

    const body = {
      title,
      contents,
    }
    const url = 'http://localhost:8080/courier/'
    axios
      .post(url, body, header)
      .then((response) => {
        dispatch({
          type: COURIER_ADD_SUCCESS,
          payload: response.data,
        })
      })
      .catch((error) => {
        dispatch({
          type: COURIER_ADD_FAIL,
          payload: error,
        })
      })
  }
}

export const getCourier = () => {
  return (dispatch) => {
    dispatch({
      type: COURIER_FETCH_REQUEST,
    })

    const header = {
      headers: {
        'Content-Type': 'application/json',
        token: sessionStorage['token'],
      },
    }

    const url = 'http://localhost:8080/courier/'
    axios
      .get(url, header)
      .then((response) => {
        dispatch({
          type: COURIER_FETCH_SUCCESS,
          payload: response.data,
        })
      })
      .catch((error) => {
        dispatch({
          type: COURIER_FETCH_FAIL,
          payload: error,
        })
      })
  }
}
