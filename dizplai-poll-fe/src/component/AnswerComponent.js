import React from 'react';
import { answerPoll } from '../service/pollService';

const AnswerComponent = ({answer, onClick}) => {

    return (
        <div>
            <button className="answer-button" onClick={onClick}>
                <p style={{zIndex: 1, display: "inline-block"}}>{answer}</p>
            </button>
        </div>
    )
}

export default AnswerComponent;