import React from 'react';
import {Box, Container} from "@mui/material";
import Header from "../Header/Header.jsx";
import {Outlet} from "react-router";
import "./Layout.css";
import Footer from "../Footer/Footer.jsx";

const Layout = () => {
    return (
        <Box className="layout-box">
            <Header/>
            <Container className="outlet-container" sx={{flexGrow: 1, my: 2}}>
                <Outlet/>
            </Container>
            <Footer/>
        </Box>
    );
};

export default Layout;
