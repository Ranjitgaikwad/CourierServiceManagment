import {
    BRANCH_ADD_FAIL,
    BRANCH_ADD_REQUEST,
    BRANCH_ADD_SUCCESS,
    BRANCH_FETCH_FAIL,
    BRANCH_FETCH_REQUEST,
    BRANCH_FETCH_SUCCESS,
  } from '../constants/branchContants'
  import axios from 'axios'
  
  export const addBranch = (name,contact,email,address,city,pincode,state,country) => {
    return (dispatch) => {
      dispatch({
        type: BRANCH_ADD_REQUEST,
      })
  
      const header = {
        headers: {
          'Content-Type': 'application/json',
          token: sessionStorage['token'],
        },
      }


    // const header ={
    //     headers:{
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         Authorization:"Bearer " + sessionStorage["token"]
    //     },
    // }
  
      const body = {
        name,
        contact,
        email,
        address,
        city,
        pincode,
        state,
        country
        
      }
      const url = 'http://localhost:8080/admin/addbranch'
      axios
        .post(url, body, header)
        .then((response) => {
          dispatch({
            type: BRANCH_ADD_SUCCESS,
            payload: response.data,
          })
        })
        .catch((error) => {
          dispatch({
            type: BRANCH_ADD_FAIL,
            payload: error,
          })
        })
    }
  }
  
  export const getBranch = () => {
    return (dispatch) => {
      dispatch({
        type: BRANCH_FETCH_REQUEST,
      })
  
      const header = {
        headers: {
          'Content-Type': 'application/json',
          token: sessionStorage['token'],
        },
      }
  
      const url = 'http://localhost:8080/admin/allbranch'
      axios
        .get(url, header)
        .then((response) => {
          dispatch({
            type: BRANCH_FETCH_SUCCESS,
            payload: response.data,
          })
        })
        .catch((error) => {
          dispatch({
            type: BRANCH_FETCH_FAIL,
            payload: error,
          })
        })
    }
  }
  