import React from 'react';
import {
    Box,
    Button,
    Typography,
    Paper,
    List,
    ListItem,
    ListItemText,
    ListItemSecondaryAction,
    IconButton,
    Divider,
    Stack
} from '@mui/material';
import {Delete, CheckCircle, Cancel} from '@mui/icons-material';
import useReservationCart from '../../../../hooks/reservationCartHooks/useReservationCart.js';

const ReservationCart = () => {
    const {
        id,
        dateCreated,
        accommodations,
        status,
        onDelete,
        onConfirm,
        onCancel
    } = useReservationCart();

    return (
        <Box sx={{maxWidth: 800, mx: 'auto', mt: 6}}>
            <Paper elevation={3} sx={{p: 4, borderRadius: 3}}>
                <Typography variant="h4" gutterBottom>
                    Reservation Cart
                </Typography>

                {id === null ? (
                    <Typography variant="body1">No active reservation cart found.</Typography>
                ) : (
                    <>
                        <Typography variant="subtitle1" gutterBottom>
                            Cart ID: {id} | Status: {status}
                        </Typography>
                        <Typography variant="subtitle2" gutterBottom>
                            Date Created: {new Date(dateCreated).toLocaleString()}
                        </Typography>

                        <Divider sx={{my: 2}}/>

                        {accommodations.length === 0 ? (
                            <Typography variant="body1">Your cart is empty.</Typography>
                        ) : (
                            <List>
                                {accommodations.map((acc) => (
                                    <ListItem key={acc.id} divider>
                                        <ListItemText
                                            primary={acc.name}
                                            secondary={`Category: ${acc.category} | Available Rooms: ${acc.numRooms}`}
                                        />
                                        <ListItemSecondaryAction>
                                            <IconButton
                                                edge="end"
                                                color="error"
                                                onClick={() => onDelete(acc.id)}
                                            >
                                                <Delete/>
                                            </IconButton>
                                        </ListItemSecondaryAction>
                                    </ListItem>
                                ))}
                            </List>
                        )}

                        <Divider sx={{my: 2}}/>

                        <Stack direction="row" spacing={2} justifyContent="flex-end">
                            <Button
                                variant="contained"
                                color="success"
                                startIcon={<CheckCircle/>}
                                onClick={onConfirm}
                                disabled={accommodations.length === 0}
                            >
                                Confirm
                            </Button>
                            <Button
                                variant="outlined"
                                color="error"
                                startIcon={<Cancel/>}
                                onClick={onCancel}
                                disabled={accommodations.length === 0}
                            >
                                Cancel
                            </Button>
                        </Stack>
                    </>
                )}
            </Paper>
        </Box>
    );
};

export default ReservationCart;
