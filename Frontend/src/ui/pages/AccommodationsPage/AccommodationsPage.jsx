import {Box, Button, CircularProgress} from "@mui/material";
import useAccommodations from "../../../hooks/accommodationHooks/useAccommodations.js";
import "./AccommodationsPage.css";
import AccommodationsGrid from "../../components/accommodations/AccommodationsGrid/AccommodationsGrid.jsx";
import AddAccommodationDialog from "../../components/accommodations/AddAccommodationDialog/AddAccommodationDialog.jsx";
import CategoryFilter from "../../components/filter/CategoryFilter.jsx";
import {useEffect, useState} from "react";

const AccommodationsPage = () => {
    const {accommodations, loading, categories, onAdd, onEdit, onDelete} = useAccommodations();
    const [addAccommodationDialogOpen, setAddAccommodationDialogOpen] = useState(false);
    const [filteredAccommodations, setFilteredAccommodations] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState(null);

    useEffect(() => {
        setFilteredAccommodations(
            selectedCategory ? accommodations.filter(a => a.category === selectedCategory) : accommodations
        );
    }, [accommodations, selectedCategory]);

    const handleCategoryChange = (category) => {
        setSelectedCategory(category);
        if (category) {
            setFilteredAccommodations(accommodations.filter(a => a.category === category));
        } else {
            setFilteredAccommodations(accommodations);
        }
    };

    return (
        <>
            <Box className="accommodations-box">
                {loading ? (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                ) : (
                    <>
                        <Box sx={{ display: "flex", justifyContent: "flex-start", gap: 20, mb: 2 }}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => setAddAccommodationDialogOpen(true)}
                            >
                                Add Accommodation
                            </Button>
                            <CategoryFilter
                                categories={categories}
                                selectedCategory={selectedCategory}
                                onCategoryChange={handleCategoryChange}
                            />
                        </Box>

                        <AccommodationsGrid
                            accommodations={filteredAccommodations}
                            onEdit={onEdit}
                            onDelete={onDelete}
                        />
                    </>
                )}
            </Box>

            <AddAccommodationDialog
                open={addAccommodationDialogOpen}
                onClose={() => setAddAccommodationDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default AccommodationsPage;
