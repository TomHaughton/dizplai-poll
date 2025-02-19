import React from 'react';

import {render, screen} from '@testing-library/react';

import {fetchLatestPoll} from '../src/service/pollService';
import PollComponent from "../src/component/PollComponent";

jest.mock('../src/service/pollService')


test('renders poll', async () => {
    const latestPollResponse = {
        id: "aa4ad4b7-5893-4695-8515-47efa24f6cdc",
        question: "Who is the best this time?",
        answers: [
            {
                id: "ad35bc17-bc39-45cd-baba-555f2f45a07f",
                answer: "Them"
            },
            {
                id: "da2e35e9-dfee-41ba-b735-b983d0653243",
                answer: "You"
            }
        ]
    };

    fetchLatestPoll.mockResolvedValue(latestPollResponse);

    render(<PollComponent/>);

    const questionElement = await screen.findByText(/Who is the best this time?/i);
    const answer1Element = await screen.findByText(/Them/i);
    const answer2Element = await screen.findByText(/You/i);

    expect(questionElement).toBeInTheDocument();
    expect(answer1Element).toBeInTheDocument();
    expect(answer2Element).toBeInTheDocument();

});