import React from 'react';
import {useNavigate, useParams} from "react-router";
import {
    Box,
    Button,
    Chip,
    CircularProgress,
    Grid,
    Typography,
    Paper,
    Stack,
    Breadcrumbs,
    Link
} from "@mui/material";
import {
    ArrowBack,
    FavoriteBorder,
    Share, Person, HotelOutlined
} from "@mui/icons-material";
import useAccommodationDetails from "../../../../hooks/accommodationHooks/useAccommodationDetails.js";
import useReservationCart from "../../../../hooks/reservationCartHooks/useReservationCart.js";
import ShoppingBasketIcon from '@mui/icons-material/ShoppingBasket';

const AccommodationDetails = () => {
    const {id} = useParams();
    const {accommodation, hostProfile} = useAccommodationDetails(id);
    const {onAdd} = useReservationCart()
    const navigate = useNavigate();

    if (!accommodation || !hostProfile) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    // const addToCart = () => {
    //     reservationCartRepository
    //         .addToCart(id)
    //         .then(() => console.log(`Successfully added accommodation with ID ${id} to card.`))
    //         .catch((error) => console.log(error));
    // };

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/accommodations");
                    }}
                >
                    Accommodations
                </Link>
                <Typography color="text.primary">{accommodation.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={4}>
                    <Grid size={{xs: 12, md: 3}}>
                        <Box sx={{
                            display: 'flex',
                            justifyContent: 'center',
                            mb: 4,
                            bgcolor: 'background.paper',
                            p: 3,
                            borderRadius: 3,
                            boxShadow: 1
                        }}>
                            {/*<Avatar*/}
                            {/*    src={product.image || "/placeholder-product.jpg"}*/}
                            {/*    variant="rounded"*/}
                            {/*    sx={{*/}
                            {/*        width: '100%',*/}
                            {/*        height: 'auto',*/}
                            {/*        objectFit: 'contain'*/}
                            {/*    }}*/}
                            {/*/>*/}
                        </Box>
                    </Grid>
                    <Grid size={{xs: 12, md: 9}}>
                        <Box sx={{mb: 3}}>
                            <Typography variant="h3" gutterBottom sx={{fontWeight: 600}}>
                                {accommodation.name}
                            </Typography>

                            <Typography variant="h4" color="primary.main" sx={{mb: 3}}>
                                {accommodation.category}
                            </Typography>

                            <Typography variant="body1" sx={{mb: 3}}>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi consequatur culpa
                                doloribus, enim maiores possimus similique totam ut veniam voluptatibus. Adipisci
                                consequatur, cum dolorem eaque enim explicabo fugit harum, id laborum non totam ut!
                                Architecto assumenda deserunt doloribus ducimus labore magnam officiis sunt. Autem culpa
                                eaque obcaecati quasi, quo vitae.
                            </Typography>

                            <Stack direction="row" spacing={1} sx={{mb: 3}}>
                                <Chip
                                    icon={<Person/>}
                                    label={`${hostProfile.name} ${hostProfile.surname}`}
                                    color="primary"
                                    variant="outlined"
                                    sx={{p: 2}}
                                />
                                <Chip
                                    icon={<HotelOutlined/>}
                                    label={`Available Rooms: ${accommodation.numRooms}`}
                                    color="secondary"
                                    variant="outlined"
                                    sx={{p: 2}}
                                />
                            </Stack>
                        </Box>
                    </Grid>
                    <Grid size={12} display="flex" justifyContent="space-between">
                        <Stack direction="row" spacing={2}>
                            <Button
                                variant="contained"
                                color="primary"
                                startIcon={<ShoppingBasketIcon/>}
                                size="large"
                                onClick={() => onAdd(id)}
                            >
                                Add to Reservation Cart
                            </Button>
                            {/*<Button*/}
                            {/*    variant="outlined"*/}
                            {/*    color="secondary"*/}
                            {/*    startIcon={<FavoriteBorder/>}*/}
                            {/*>*/}
                            {/*    Wishlist*/}
                            {/*</Button>*/}
                            <Button
                                variant="outlined"
                                startIcon={<Share/>}
                            >
                                Share
                            </Button>
                        </Stack>
                        <Button
                            variant="outlined"
                            startIcon={<ArrowBack/>}
                            onClick={() => navigate("/accommodations")}
                        >
                            Back to Accommodations
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default AccommodationDetails;
