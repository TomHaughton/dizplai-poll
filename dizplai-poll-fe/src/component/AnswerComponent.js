import React from 'react';
import { answerPoll } from '../service/pollService';

const AnswerComponent = ({answer, answerId, pollId, onClick}) => {

    const handleAnswerPoll = async () => {
        try {
            console.log(answer, answerId, pollId)
            await answerPoll(pollId, answerId);
        } catch (error) {
            console.error('Failed to save message', error);
        }
    };

    return (
        <div>
            <button className="answer-button" onClick={onClick}>
                <p style={{zIndex: 1, display: "inline-block"}}>{answer}</p>
            </button>
        </div>
    )
}

export default AnswerComponent;