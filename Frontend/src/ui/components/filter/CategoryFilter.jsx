import React from 'react';
import { ToggleButton, ToggleButtonGroup } from '@mui/material';

const CategoryFilter = ({ categories, selectedCategory, onCategoryChange }) => {
    const handleCategoryChange = (event, newCategory) => {
        if (newCategory !== null) {
            onCategoryChange(newCategory);
        }
    };

    return (
        <ToggleButtonGroup
            value={selectedCategory}
            exclusive
            onChange={handleCategoryChange}
            aria-label="Accommodation Category"
            sx={{ mb: 2 }}
        >
            {categories.map((category) => (
                <ToggleButton key={category} value={category} aria-label={category}>
                    {category}
                </ToggleButton>
            ))}
        </ToggleButtonGroup>
    );
};

export default CategoryFilter;
