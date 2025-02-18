import React from 'react';
import {answerPoll} from '../service/pollService';

const ResultComponent = ({answer, answerId, percentage}) => {

    return (
        <div>
            <button className="answer-button"
                    style={{
                        background: `linear-gradient(to right, #9305fa ${percentage}%, #5c2cd3 ${percentage + 17}%)`,
                    }}
                    disabled="true">
                <p style={{zIndex: 1, display: "inline-block"}}>{answer}</p>
                <p style={{zIndex: 10, display: "inline-block", float: "right"}}>{percentage}%</p>
            </button>
            {/*<p>{answer}</p>*/}
            {/*<p>{percentage}%</p>*/}
        </div>
    )
}

export default ResultComponent;