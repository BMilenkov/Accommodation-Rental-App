import React from 'react';
import {
    Box,
    Typography,
    Paper,
    Grid,
    Chip,
    Stack,
    CircularProgress,
    Breadcrumbs,
    Link,
    Avatar
} from "@mui/material";
import {Person, Public, AccountBox, Badge} from "@mui/icons-material";
import {useNavigate, useParams} from "react-router";
import useHostDetails from "../../../../hooks/hostHooks/useHostDetails.js";

const HostDetails = () => {
    const {id} = useParams();
    const {host, country} = useHostDetails(id);
    const navigate = useNavigate();

    if (!host || !country) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/hosts");
                    }}
                >
                    Hosts
                </Link>
                <Typography color="text.primary">{host.name} {host.surname}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={4}>
                    <Grid item xs={12} md={3}>
                        <Box sx={{
                            display: 'flex',
                            justifyContent: 'center',
                            alignItems: 'center',
                            flexDirection: 'column'
                        }}>
                            <Avatar sx={{width: 120, height: 120, mb: 2}}>
                                <Person fontSize="large"/>
                            </Avatar>
                            <Typography variant="h5" sx={{fontWeight: 600}}>
                                {host.name} {host.surname}
                            </Typography>
                            <Typography variant="body2" color="text.secondary">
                                @{host.username}
                            </Typography>
                        </Box>
                    </Grid>

                    <Grid item xs={12} md={9}>
                        <Stack spacing={2}>
                            <Chip
                                icon={<Public/>}
                                label={`Country: ${country.name} (${country.continent})`}
                                color="primary"
                                variant="outlined"
                                sx={{p: 2, width: 'fit-content'}}
                            />
                            <Chip
                                icon={<AccountBox/>}
                                label={`Username: ${host.username}`}
                                color="secondary"
                                variant="outlined"
                                sx={{p: 2, width: 'fit-content'}}
                            />
                            <Chip
                                icon={<Badge/>}
                                label={"Role HOST"}
                                color="default"
                                variant="outlined"
                                sx={{p: 2, width: 'fit-content'}}
                            />
                        </Stack>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default HostDetails;
