import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
} from "@mui/material";
import useCountries from "../../../../hooks/countryHooks/useCountries.js";
import useUsers from "../../../../hooks/nonHostUserHooks/useUsers.js";

const initialFormData = {
    "country": "",
    "user": ""
};

const AddHostDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);
    const countries = useCountries();
    const users = useUsers();

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
            <DialogTitle>Add New Host</DialogTitle>
            <DialogContent>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Country:</InputLabel>
                    <Select
                        name="country"
                        value={formData.country}
                        onChange={handleChange}
                        label="Country"
                        variant="outlined">
                        {countries.countries.map((country) => (
                            <MenuItem key={country.id}
                                      value={country.id}>{country.name} - {country.continent}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>User:</InputLabel>
                    <Select
                        name="user"
                        value={formData.hostProfile}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined">
                        {users.users.map((user) => (
                            <MenuItem key={user.username} value={user.username}>
                                {user.username} ({user.name} {user.surname})
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddHostDialog;
