import {useEffect, useState} from "react";
import accommodationRepository from "../../repository/accommodationRepository.js";
import hostProfileRepository from "../../repository/hostProfileRepository.js";


const useAccommodationDetails = (id) => {
    const [state, setState] = useState({
        "accommodation": null,
        "hostProfile": null
    });

    useEffect(() => {
        accommodationRepository.findById(id)
            .then((response) => {
                setState(prevState => ({
                    ...prevState, "accommodation": response.data
                }));
                hostProfileRepository.findById(response.data.hostProfile).then((response) => {
                    setState(prevState => ({...prevState, "hostProfile": response.data}));
                }).catch((error) => console.log(error));
            }).catch((error) => console.log(error));

    }, [id]);

    return state;
};

export default useAccommodationDetails;
