import axios from 'axios';

export const fetchLatestPoll = async () => {
    try {
        const response = await axios.get(`/api/v1/polls`);
        return response.data;
    } catch (error) {
        console.error('Error fetching latest poll:', error);
        throw error;
    }
};

export const answerPoll = async (pollId, pollAnswerId) => {
    try {
        const response = await axios.post(`/api/v1/polls/${pollId}/votes`, { pollAnswerId });
        return response.data;
    } catch (error) {
        console.error('Error answering poll:', error);
        throw error;
    }
};