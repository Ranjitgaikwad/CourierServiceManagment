import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import React, { Component } from 'react'
import CourierService from '../../service/CourierService'


class CourierListScreen extends Component {
    constructor(props) {
        super(props)

        this.state = {
                consignment: []
        }
    //     this.addcourier = this.addcourier.bind(this);
    //     this.editcourier = this.editcourier.bind(this);
    //     this.deletecourier = this.deletecourier.bind(this);
     }

    // console.log(this.state.courier);

    // deletecourier(id){
    //     courierService.deletecourier(id).then( res => {
    //         this.setState({courier: this.state.courier.filter(courier => courier.id !== id)});
    //     });
    // }
    viewCourier(Id){
        this.props.history.push(`/view-courier/${Id}`);
    }
    editcourier(Id){
        this.props.history.push(`/edit-courier/${Id}`);
    }

    componentDidMount(){
        CourierService.getcourier().then((res) => {
            this.setState({ consignment: res.data});
        });
    }

    addcourier(){
        this.props.history.push('/addcourierfn');
    }

    back(){
        this.props.history.push('/employeeb');
    }


    

    render() {
        return (
            <div>
                
                 <h2 className="text-center">Couriers List</h2>
                 <div className = "row">
                 {/* <button className="btn btn-primary btn-lg" style={{marginBottom: "10px"}} href="/addcourier" role="button"> */}
                    <button style={{marginBottom: "10px", width:"150px"}} className="btn btn-primary" onClick={this.addcourier.bind(this)}> Add Courier</button>
                 </div>
                 <div className = "row">
                    <button style={{marginBottom: "10px", width:"200px"}} className="btn btn-success" onClick={this.back.bind(this)}> back to Employee board</button>
                 </div>
                 <br></br>
                 <div className = "row">
                        <table className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                <th> Id</th>
                                    <th> Tracking id</th>
                                    {/* <th> Courier Type</th> */}
                                    <th> Sender Name</th>
                                    {/* <th> Sender Contact</th> */}
                                    {/* <th> Sender Address</th> */}
                                    <th> Sender City</th>
                                    {/* <th> Sender State</th> */}
                                    {/* <th> Sender PIN</th> */}
                                    {/* <th> Sender Country</th> */}
                                    <th> Recipient Name</th>
                                    {/* <th> Recipient Contact</th>
                                    <th> Recipient Address</th> */}
                                    <th> Recipient City</th>
                                    {/* <th> Recipient State</th>
                                    <th> Recipient PIN</th>
                                    <th> Recipient Country</th> */}
                                    {/* <th> Courier Weight</th> */}
                                    <th> Courier Mode</th>
                                    {/* <th> Create Date</th>
                                    <th> Update Date</th> */}
                                    {/* <th> Price</th> */}
                                    <th> Status</th>
                                    <th> Remark</th>
                                    <th> Action</th>

                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.consignment.map(
                                        courier => 
                                        <tr key = {courier.courierId}>
                                             <td> {courier.courierId}</td>
                                             <td> {courier.trackingId}</td>
                                             {/* <td> {courier.courierType} </td>    */}
                                             <td> {courier.senderName}</td>
                                             {/* <td> {courier.senderContact}</td> */}
                                             {/* <td> {courier.senderAddress}</td> */}
                                             <td> {courier.senderCity}</td>
                                             {/* <td> {courier.senderState}</td>
                                             <td> {courier.senderPin}</td>
                                             <td> {courier.senderCountry}</td> */}
                                             <td> {courier.recipientName}</td>
                                             {/* <td> {courier.recipientContact}</td>
                                             <td> {courier.recipientAddress}</td> */}
                                             <td> {courier.recipientCity}</td>
                                             {/* <td> {courier.recipientState}</td>
                                             <td> {courier.recipientPin}</td>
                                             <td> {courier.recipientCountry}</td>
                                             <td> {courier.courierWeight}</td> */}
                                             <td> {courier.courierMode}</td>
                                             {/* <td> {courier.createAt}</td>
                                             <td> {courier.updateAt}</td>
                                             <td> {courier.price}</td> */}
                                             <td> {courier.status}</td>
                                             <td> {courier.remark}</td>
                                             
                                             
                                             <td>
                                                 <button onClick={ () => this.editCourier(courier.id)} className="btn btn-info">Update </button>
                                                 {/* <button style={{marginLeft: "10px"}} onClick={ () => this.deleteCourier(courier.id)} className="btn btn-danger">Delete </button> */}
                                                 <button style={{marginLeft: "10px"}} onClick={ () => this.viewCourier(courier.courierId)} className="btn btn-info">View </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
        )
    }
}

export default CourierListScreen