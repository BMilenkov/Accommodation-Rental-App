import React, {useEffect, useState} from 'react';
import hostProfileRepository from "../../repository/hostProfileRepository.js";
import countryRepository from "../../repository/countryRepository.js";

const useHostDetails = (id) => {
    const [state, setState] = useState({
        "host": null,
        "country": null
    });

    useEffect(() => {
        hostProfileRepository.findById(id).then((response) => {
            setState(prevState => ({
                ...prevState, "host": response.data
            }));
            countryRepository.findById(response.data.country).then((response) => {
                setState(prevState => ({
                    ...prevState, "country": response.data
                }));
            }).catch((error) => console.log(error));
        }).catch((error) => console.log(error));

    }, [id]);

    return state
};

export default useHostDetails;