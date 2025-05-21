import React from 'react';
import { AppBar, Box, Toolbar, Typography } from '@mui/material';
import './Footer.css';


const Footer = () => {
    return (
        <Box sx={{ mt: 4 }}>
            <AppBar position="static" component="footer" color="primary" sx={{ top: 'auto', bottom: 0 }}>
                <Toolbar sx={{ justifyContent: 'center', flexWrap: 'wrap' }}>
                    <Typography variant="body2" color="inherit">
                        © {new Date().getFullYear()} Accommodation Rentals App
                    </Typography>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Footer;
