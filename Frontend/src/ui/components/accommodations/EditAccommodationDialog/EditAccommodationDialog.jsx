import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import useAccommodations from "../../../../hooks/accommodationHooks/useAccommodations.js";
import useHostProfiles from "../../../../hooks/hostHooks/useHostProfiles.js";

const EditAccommodationDialog = ({open, onClose, accommodation, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": accommodation.name,
        "category": accommodation.category,
        "hostProfile": accommodation.hostProfile,
        "numRooms": accommodation.numRooms
    })
    const categories = useAccommodations().categories
    const hosts = useHostProfiles()

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(accommodation.id, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Accommodation</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                        variant="outlined">
                        {categories.map((category) => (
                            <MenuItem key={category} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="hostProfile"
                        value={formData.hostProfile}
                        onChange={handleChange}
                        label="Host Profile"
                        variant="outlined">
                        {hosts.hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>{host.username}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <TextField
                margin="dense"
                label="Rooms"
                name="numRooms"
                type="number"
                value={formData.numRooms}
                onChange={handleChange}
                fullWidth
            />
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditAccommodationDialog;
