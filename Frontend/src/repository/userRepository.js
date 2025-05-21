import axiosInstance from "../axios/axios.js";

const userRepository = {

    register: async (data) => {
        return await axiosInstance.post('/user/register', data)
    },
    login: async (data) => {
        return await axiosInstance.post('/user/login', data)
    },
    findAllNonHost: async () => {
        return await axiosInstance.get('/user/nonHost');
    },
}

export default userRepository;