// import React, {useState} from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import {useNavigate} from "react-router";

import './HostCard.css';
import {useState} from "react";
import EditHostDialog from "../EditHostDialog/EditHostDialog.jsx";
import DeleteHostDialog from "../DeleteHostDialog/DeleteHostDialog.jsx";  // Import the external CSS


const HostCard = ({host, onEdit, onDelete}) => {
    const navigate = useNavigate();
    const [editHostDialogOpen, setEditHostDialogOpen] = useState(false);
    const [deleteHostDialogOpen, setDeleteHostDialogOpen] = useState(false);

    return (
        <>
            <Card className="host-card">
                <CardContent className="card-content">
                    <Box className="card-header">
                        <Typography variant="h5" className="host-name">
                            {host.name}
                        </Typography>
                        <Typography variant="h5" className="host-name">
                            {host.surname}
                        </Typography>
                    </Box>

                    <Box className="info-right">
                        <Typography variant="body1" className="category">
                            Role: {host.role === 'ROLE_HOST' ? 'HOST' : 'USER'}
                        </Typography>
                    </Box>
                </CardContent>
                <Typography variant="body2" className="description">
                    Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab aperiam assumenda blanditiis cum
                    ducimus enim modi natus odit quibusdam veritatis.
                </Typography>
                <CardActions className="card-actions">
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon/>}
                        onClick={() => navigate(`/host/${host.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon/>}
                            className="edit-btn"
                            onClick={() => setEditHostDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon/>}
                            onClick={() => setDeleteHostDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>

            <EditHostDialog
                open={editHostDialogOpen}
                onClose={() => setEditHostDialogOpen(false)}
                host={host}
                onEdit={onEdit}
            />
            <DeleteHostDialog
                open={deleteHostDialogOpen}
                onClose={() => setDeleteHostDialogOpen(false)}
                host={host}
                onDelete={onDelete}
            />
        </>
    );
};

export default HostCard;
