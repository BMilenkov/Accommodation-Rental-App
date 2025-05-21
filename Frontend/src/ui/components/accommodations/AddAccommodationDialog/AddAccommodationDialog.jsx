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
import useHostProfiles from "../../../../hooks/hostHooks/useHostProfiles.js";
import useAccommodations from "../../../../hooks/accommodationHooks/useAccommodations.js";

const initialFormData = {
    "name": "",
    "category": "",
    "hostProfile": "",
    "numRooms": ""
};

const AddAccommodationDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);
    const accommodations = useAccommodations();
    const hosts = useHostProfiles();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };
    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Accommodation</DialogTitle>
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
                    <InputLabel>Category:</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                        variant="outlined">
                        {accommodations.categories.map((category) => (
                            <MenuItem key={category} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host Profile:</InputLabel>
                    <Select
                        name="hostProfile"
                        value={formData.hostProfile}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined">
                        {hosts.hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>{host.username}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    margin="dense"
                    label="Number of Rooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddAccommodationDialog;
