import hostProfileRepository from "../../repository/hostProfileRepository.js";
import {useCallback, useEffect, useState} from "react";

const initialState = {
    "hosts": [],
    "loading": true
}

const useHosts = () => {
    const [state, setState] = useState(initialState)

    const fetchHosts = useCallback(() => {
        hostProfileRepository.findAll()
            .then((response) => {
                setState({
                    "hosts": response.data,
                    "loading": false,
                });
            })
            .catch((error) => console.log(error));
    }, []);

    const onAdd = useCallback((data) => {
        hostProfileRepository
            .add(data)
            .then(() => {
                console.log("Successfully added a new host.");
                fetchHosts();
            })
            .catch((error) => console.log(error));
    }, [fetchHosts]);

    const onEdit = useCallback((id, data) => {
        hostProfileRepository
            .edit(id, data)
            .then(() => {
                console.log(`Successfully edited the host with ID ${id}.`);
                fetchHosts();
            })
            .catch((error) => console.log(error));
    }, [fetchHosts]);

    const onDelete = useCallback((id) => {
        hostProfileRepository
            .delete(id)
            .then(() => {
                console.log(`Successfully deleted the host with ID ${id}.`);
                fetchHosts();
            })
            .catch((error) => console.log(error));
    }, [fetchHosts]);

    useEffect(() => {
        fetchHosts()
    }, [fetchHosts]);

    return {...state, onAdd: onAdd, onEdit: onEdit, onDelete: onDelete};
}

export default useHosts;
