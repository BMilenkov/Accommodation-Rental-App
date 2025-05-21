import axiosInstance from "../axios/axios.js";

const hostProfileRepository = {
    findAll: async () => {
        return await axiosInstance.get('/hostProfiles');
    },
    findById: async (id) => {
        return await axiosInstance.get(`/hostProfiles/${id}`)
    },
    add: async (data) => {
        return await axiosInstance.post('/hostProfiles/add', data);
    },
    edit: async (id, data) => {
        return await axiosInstance.put(`/hostProfiles/edit/${id}`, data);
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/hostProfiles/delete/${id}`);
    },
}

export default hostProfileRepository;