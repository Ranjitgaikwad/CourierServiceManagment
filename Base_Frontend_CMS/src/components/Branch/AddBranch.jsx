import axios from 'axios'
import React, { useState } from 'react'
import { useSelector } from 'react-redux'
// import { toast } from 'react-toastify'
// import Header from '../../Components/Header'

const BranchAdd = (props) => {

    //const userSignin = useSelector(store => store.userSignIn)

    const url_AddBranch = "http://localhost:8080/admin/addbranch"

    const header = {
        headers: {
            // 'Accept': 'application/json',
            withCredentials: false,
            'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
            "Content-type": "application/json",
            "Authorization": "Bearer " + sessionStorage.getItem("token")
        },
    }

    // const [trackingId, settrackingId] = useState("")
    const [name, setname] = useState("")
    const [contact, setcontact] = useState("")
    const [email, setemail] = useState("")
    const [address, setaddress] = useState("")
    const [city, setcity] = useState("")
    const [pincode, setpincode] = useState("")
    const [state, setstate] = useState("")
    const [country, setcountry] = useState("")


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
    console.log(body);
    const onAddBranch = () => {
        axios.post(url_AddBranch, body, header)
            .then(response => {
                // message.success("Address added successfully")
                props.history.push("/branchlist")
            })
            .catch(error => {
                alert(error.message)
            })
    }

    return (

        <div className="Screen">
            <div className="row">
                <div className="col-md-3">
                    <div className="col-md-20">

                        <div className="mb-3">
                            <h5 class="form-label">Name</h5>
                            <input  onChange={e => { setname(e.target.value) }} className="form-control" placeholder="" />
                        </div>

                        <div className="mb-3">
                            <h5 class="form-label">Contact</h5>
                            <input onChange={e => { setcontact(e.target.value) }} className="form-control" type="text" placeholder=" " />
                        </div>

                        <div className="mb-3">
                            <h5 class="form-label">Email</h5>
                            <input onChange={e => { setemail(e.target.value) }} className="form-control" type="text" placeholder="" />
                        </div>
            
                        <div className="mb-3">
                            <h5 class="form-label">Address</h5>
                            <input onChange={e => { setaddress(e.target.value) }} className="form-control" type="text" placeholder="" />
                        </div>
                        <div className="mb-3">
                            <h5 class="form-label">City</h5>
                            <input onChange={e => { setcity(e.target.value) }} className="form-control" type="text" placeholder="" />
                        </div>
                        <div className="mb-3">
                        <h5 class="form-label">Pincode</h5>
                            <input onChange={e => { setpincode(e.target.value) }} className="form-control" type="text" placeholder=""/>
                        </div>
                        <div className="mb-3">
                            <h5 class="form-label">state</h5>
                            <input onChange={e => { setstate(e.target.value) }} className="form-control" type="text" placeholder="" />
                        </div>
                        <div className="mb-3">
                            <h5 class="form-label">Country</h5>
                            <input onChange={e => { setcountry(e.target.value) }} className="form-control" type="text" placeholder="" />
                        </div>
                        
                        <div className="mb-3">
                            <br />
                            <button onClick={onAddBranch} className="btn btn-outline-primary"> Add address </button>
                            <button onClick={() => { props.history.push('/branchlist') }} className="btn btn-outline-danger NextBtn"> Cancel </button>
                        </div>

                    </div>
                    <div className="col-md-3"></div>
                </div>
            </div>
        </div>

    )
}

export default BranchAdd