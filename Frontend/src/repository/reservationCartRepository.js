import axiosInstance from "../axios/axios.js";

const reservationCartRepository = {

    getActive: async () => {
        return await axiosInstance.get("/reservation-cart")
    },
    addToCart: async (id) => {
        return await axiosInstance.post(`/reservation-cart/add-accommodation/${id}`)
    },
    deleteFromCart: async (id) => {
        return await axiosInstance.post(`/reservation-cart/remove-accommodation/${id}`)
    },
    confirm: async () => {
        return await axiosInstance.post(`/reservation-cart/confirm`)
    },
    cancel: async () => {
        return await axiosInstance.post(`/reservation-cart/cancel`)
    },
};

export default reservationCartRepository;