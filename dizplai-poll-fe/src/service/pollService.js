import axios from 'axios';

export const fetchLatestPoll = async () => {
    try {
        const response = await axios.get(`/api/v1/polls`);
        return response.data;
    } catch (error) {
        console.error('Error fetching messages:', error);
        throw error;
    }
};

export const answerPoll = async (pollId, pollAnswerId) => {
    try {
        const response = await axios.post(`/api/v1/polls/${pollId}/votes`, { pollAnswerId });
        return response.data;
    } catch (error) {
        console.error('Error saving message:', error);
        throw error;
    }
};