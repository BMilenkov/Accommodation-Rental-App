import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogTitle,
    FormControl,
    InputLabel, MenuItem, Select,
} from "@mui/material";
import useCountries from "../../../../hooks/countryHooks/useCountries.js";

const EditHostDialog = ({open, onClose, host, onEdit}) => {
    const [formData, setFormData] = useState({
        "country": host.country,
        "user": host.username
    })

    const countries = useCountries();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(host.id, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Host</DialogTitle>
            <FormControl fullWidth margin="dense">
                <InputLabel>Country</InputLabel>
                <Select
                    name="country"
                    value={formData.country}
                    onChange={handleChange}
                    label="Country"
                    variant="outlined">
                    {countries.countries.map((country) => (
                        <MenuItem key={country.id} value={country.id}>{country.name} - {country.continent}</MenuItem>
                    ))}
                </Select>
            </FormControl>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="warning">Edit</Button>
            </DialogActions>
        </Dialog>
    );
};

export default EditHostDialog;
