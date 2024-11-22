import React, {useEffect} from 'react';
import './App.scss';
import {Route, Routes, useLocation, useNavigate} from "react-router-dom";
import {SignIn} from "./pages/sign-in";
import Home from "./pages/home/Home";
import Company from "./pages/company/Company"
import 'material-icons/iconfont/material-icons.css';

const App = () => {
    const navigate = useNavigate()
    const {pathname} = useLocation()

    useEffect(() => {
        if (pathname === '/') {
            navigate("/sign-in")
        }
    }, [pathname, navigate])

    return (
        <div className="app">
            <Routes>
                <Route path={"/sign-in"} element={<SignIn/>}/>
                <Route path={"/home"} element={<Home/>}/>
                <Route path={"/company"} element={<Company/>}/>
            </Routes>
        </div>
    );
}

export default App;