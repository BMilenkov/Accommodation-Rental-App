import React from 'react';
import AccommodationsPage from "./ui/pages/AccommodationsPage/AccommodationsPage.jsx";
import HostsPage from "./ui/pages/HostsPage/HostsPage.jsx";

import {BrowserRouter, Routes, Route} from "react-router";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import AccommodationDetails from "./ui/components/accommodations/AccommodationDetails/AccommodationDetails.jsx";
import HostDetails from "./ui/components/hosts/HostDetails/HostDetails.jsx";
import Login from "./ui/components/auth/Login/Login.jsx"
import Register from "./ui/components/auth/Register/Register.jsx"
import ReservationCart from "./ui/components/cart/ReservationCart/ReservationCart.jsx"
import ProtectedRoute from "./ui/components/routing/ProtectedRoute/ProtectedRoute.jsx";

const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route element={<ProtectedRoute/>}>
                        <Route path="accommodations" element={<AccommodationsPage/>}/>
                        <Route path="accommodation/:id" element={<AccommodationDetails/>}/>
                        <Route path="hosts" element={<HostsPage/>}/>
                        <Route path="host/:id" element={<HostDetails/>}/>
                        <Route path="reservation-cart" element={<ReservationCart/>}/>
                    </Route>
                </Route>
            </Routes>
        </BrowserRouter>
    );
};

export default App;

