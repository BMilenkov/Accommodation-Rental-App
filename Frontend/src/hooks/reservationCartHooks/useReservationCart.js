import {useCallback, useEffect, useState} from "react";
import reservationCartRepository from "../../repository/reservationCartRepository.js";

const useReservationCart = () => {

    const [reservationCart, setReservationCart] = useState({
        "id": null,
        "dateCreated": null,
        "user": null,
        "accommodations": [],
        "status": null,
    })

    const activeCarts = useCallback(() => {
        reservationCartRepository.getActive()
            .then((response) => setReservationCart(response.data))
            .catch((error) => console.log(error));

    }, []);

    const onAdd = useCallback((id) => {
        reservationCartRepository.addToCart(id)
            .then(() => {
                console.log("Successfully added a new accommodation to Cart.");
                activeCarts()
            }).catch((error) => console.log(error));
    }, [activeCarts]);

    const onDelete = useCallback((data) => {
        reservationCartRepository.deleteFromCart(data)
            .then(() => {
                console.log("Successfully deleted an accommodation from Cart.");
                activeCarts()
            }).catch((error) => console.log(error));
    }, [activeCarts]);

    const onConfirm = useCallback(() => {
        reservationCartRepository.confirm()
            .then(() => {
                console.log("Successfully confirmed Reservation Cart.");
                activeCarts()
            }).catch((error) => console.log(error));
    }, [activeCarts]);

    const onCancel = useCallback(() => {
        reservationCartRepository.cancel()
            .then(() => {
                console.log("Successfully canceled Reservation Cart.");
                activeCarts()
            }).catch((error) => console.log(error));
    }, [activeCarts]);

    useEffect(() => {
        activeCarts()
    }, [activeCarts]);

    return {...reservationCart, onAdd: onAdd, onDelete: onDelete, onConfirm: onConfirm, onCancel: onCancel}
}

export default useReservationCart