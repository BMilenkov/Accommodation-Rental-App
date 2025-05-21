import React from 'react';
import {Navigate, Outlet} from "react-router";
import useAuth from "../../../../hooks/authHook/useAuth.js";

const ProtectedRoute = ({role}) => {
    const {user, loading} = useAuth();

    if (loading)
        return null;

    if (user === null)
        return <Navigate to="/login" replace/>;

    if (role && !user.roles.includes(role))
        return <Navigate to="/login" replace/>;

    return <Outlet/>;
};

export default ProtectedRoute;
