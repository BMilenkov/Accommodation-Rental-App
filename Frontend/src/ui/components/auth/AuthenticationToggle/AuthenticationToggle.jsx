import React from 'react';
import {Button} from "@mui/material";
import {useNavigate} from "react-router";
import useAuth from "../../../../hooks/authHook/useAuth.js";

const AuthenticationToggle = () => {
    const {logout, isLoggedIn} = useAuth();

    const navigate = useNavigate();

    const handleLogin = () => {
        navigate("/login");
    };

    const handleLogout = () => {
        logout();
        navigate("/login");
    };

    return (
        <Button
            color="inherit"
            variant={!isLoggedIn ? "text" : "outlined"}
            onClick={!isLoggedIn ? handleLogin : handleLogout}
        >
            {!isLoggedIn ? "Login" : "Logout"}
        </Button>
    );
};

export default AuthenticationToggle;
