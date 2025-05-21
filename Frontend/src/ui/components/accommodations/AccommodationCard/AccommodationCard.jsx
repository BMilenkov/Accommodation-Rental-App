import React, { useState } from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { Box, Button, Card, CardActions, CardContent, Typography } from "@mui/material";
import EditAccommodationDialog from "../EditAccommodationDialog/EditAccommodationDialog.jsx";
import DeleteAccommodationDialog from "../DeleteAccommodationDialog/DeleteAccommodationDialog.jsx";

import './AccommodationCard.css';
import {useNavigate} from "react-router";

const AccommodationCard = ({ accommodation, onEdit, onDelete }) => {
    const navigate = useNavigate();
    const [editAccommodationDialogOpen, setEditAccommodationOpen] = useState(false);
    const [deleteAccommodationDialogOpen, setDeleteAccommodationOpen] = useState(false);

    return (
        <>
            <Card className="accommodation-card">
                <CardContent className="card-content">
                    <Box className="card-header">
                        <Typography variant="h5" className="accommodation-name">
                            {accommodation.name}
                        </Typography>
                        <Box className={`rented-label ${accommodation.isRented ? 'rented' : 'available'}`}>
                            {accommodation.isRented ? "Rented" : "Available"}
                        </Box>
                    </Box>

                    <Box className="info-right">
                        <Typography variant="body1" className="category">
                            Category: {accommodation.category}
                        </Typography>
                        <Typography variant="body2" className="rooms">
                            Rooms: {accommodation.numRooms}
                        </Typography>
                    </Box>
                </CardContent>
                <Typography variant="body2" className="description">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum ducimus enim modi natus odit quibusdam veritatis.
                </Typography>
                <CardActions className="card-actions">
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon />}
                        onClick={() => navigate(`/accommodation/${accommodation.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon />}
                            className="edit-btn"
                            onClick={() => setEditAccommodationOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon />}
                            onClick={() => setDeleteAccommodationOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>

            <EditAccommodationDialog
                open={editAccommodationDialogOpen}
                onClose={() => setEditAccommodationOpen(false)}
                accommodation={accommodation}
                onEdit={onEdit}
            />
            <DeleteAccommodationDialog
                open={deleteAccommodationDialogOpen}
                onClose={() => setDeleteAccommodationOpen(false)}
                accommodation={accommodation}
                onDelete={onDelete}
            />
        </>
    );
};

export default AccommodationCard;
