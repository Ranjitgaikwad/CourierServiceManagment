import './App.css'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import HomeScreen from './screens/HomeScreen'
import AddNoteScreen from './screens/AddNoteScreen'
import AboutScreen from './screens/AboutScreen'
import Navigation from './components/Navigation'
import Signup_admin from './screens/Signup_admin'
import Signup_employee from './screens/Signup_employee'
import Signup_customer from './screens/Signup_customer'
import Signin_admin from './screens/Signin_admin'
import Signin_customer from './screens/Signin_customer'
import Signin_employee from './screens/Signin_employee'
import Adminboard from './screens/Adminboard'
import CreateEmployeeComponent from './components/user/CreateEmployeeComponent';
import ViewEmployeeComponent from './components/user/ViewEmployeeComponent';
import ListEmployeeComponent from './components/user/ListEmployeeComponent';
import BranchListScreen from './screens/BranchListScreen'
import AddBranchScreen from './screens/AddBranchScreen'
import CourierListScreen from './components/courier/CourierListScreen'
import ViewCourierComponent from './components/courier/ViewCourierComponent'
import Employeeboard from './screens/Employeeboard'

import addcourierfun from './components/courier/addcourierfun'
import CourierListScreenAdmin from './components/courier/CourierListScreenAdmin'
import AddBranch from './components/Branch/AddBranch'
import CourierrAddScreenCust from './components/courier/addCourierCust'
import CustomerBoard from './screens/CustomerBoard'
function App() {
  return (
    <div>
      <Router>
        <Navigation />
        <div className="container">
          <Switch>
            {/* <Route path="/" component={HomeScreen} /> */}
            <Route path="/" exact component={HomeScreen} />
            <Route exact path={"/signin_admin"} component={Signin_admin} />
            <Route exact path={"/signin_employee"} component={Signin_employee} />
            <Route exact path={"/signin_customer"} component={Signin_customer} />
            <Route path="/signup_admin" component={Signup_admin} />
            <Route path="/signup_employee" component={Signup_employee} />
            <Route path="/signup_customer" component={Signup_customer} />
            <Route path="/add-note" component={AddNoteScreen} />
            <Route path="/about" component={AboutScreen} />
            <Route path="/admin" component={Adminboard} />
            <Route path="/home" component={HomeScreen} />
            <Route path = "/employee" exact component = {ListEmployeeComponent}></Route>
            <Route path = "/employees" component = {ListEmployeeComponent}></Route>
            <Route path="/add-employee/:id" component={CreateEmployeeComponent}></Route>
            <Route path="/view-employee/:id" component={ViewEmployeeComponent}></Route>
            <Route path="/branchlist" component={BranchListScreen}></Route>
            {/* <Route path="/add-branch" component={AddBranchScreen}></Route> */}
            <Route path="/courierlist" component={CourierListScreen}></Route>
            <Route path="/view-courier/:id" component={ViewCourierComponent}></Route>
            <Route path="/employeeb" component={Employeeboard}></Route>
         
            <Route path="/addcourierfn" component={addcourierfun}></Route>
            <Route path="/courierlist_admn" component={CourierListScreenAdmin}></Route>
            <Route exact path="/addbranch" component={AddBranch}></Route>
            <Route path="/bookcourier" component={CourierrAddScreenCust}></Route>
            <Route path="/custboard" component={CustomerBoard}></Route>
          </Switch>
        </div>
      </Router>
    </div>
  )
}

export default App
