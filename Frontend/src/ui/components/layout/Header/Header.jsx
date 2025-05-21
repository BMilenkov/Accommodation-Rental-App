import React from 'react';
import {Link} from "react-router";
import {
    AppBar,
    Box,
    Button,
    IconButton,
    Toolbar,
    Typography,
    Stack,
    Tooltip
} from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';
import PersonIcon from '@mui/icons-material/Person';

import AuthenticationToggle from '../../auth/AuthenticationToggle/AuthenticationToggle.jsx';
import './Header.css';
import useAuth from "../../../../hooks/authHook/useAuth.js";

const pages = [
    { path: '/', name: 'Home' },
    { path: '/accommodations', name: 'Accommodations' },
    { path: '/hosts', name: 'Accommodation Hosts' }
];

const Header = () => {
    const { user } = useAuth();

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" sx={{ px: 2 }}>
                <Toolbar disableGutters sx={{ justifyContent: 'space-between' }}>
                    <Stack direction="row" alignItems="center" spacing={2}>
                        <IconButton size="large" edge="start" color="inherit" aria-label="menu">
                            <MenuIcon />
                        </IconButton>
                        <Typography variant="h6" component="div" sx={{ fontWeight: 600 }}>
                            Accommodation RENTAL
                        </Typography>
                        {pages.map((page) => (
                            <Button
                                key={page.name}
                                component={Link}
                                to={page.path}
                                color="inherit"
                                sx={{
                                    textTransform: 'none',
                                    '&:hover': {
                                        color: 'white',
                                    }
                                }}
                            >
                                {page.name}
                            </Button>
                        ))}
                        <Button
                            component={Link}
                            to="/reservation-cart"
                            color="inherit"
                            startIcon={<ShoppingBasketIcon />}
                            sx={{
                                textTransform: 'none',
                                fontWeight: 'bold',
                                border: '1px solid white',
                                borderRadius: 2,
                                ml: 2,
                                '&:hover': {
                                    color: 'white',
                                }
                            }}
                        >
                            Reservation Cart
                        </Button>
                    </Stack>

                    <Stack direction="row" alignItems="center" spacing={2}>
                        {user && (
                            <Tooltip title="Logged in user">
                                <Stack direction="row" alignItems="center" spacing={1}>
                                    <PersonIcon fontSize="small" />
                                    <Typography variant="body1" sx={{ color: 'white' }}>
                                        {user.sub}
                                    </Typography>
                                </Stack>
                            </Tooltip>
                        )}
                        <AuthenticationToggle />
                    </Stack>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Header;
