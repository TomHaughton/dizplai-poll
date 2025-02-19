import React from 'react';

const ResultComponent = ({answer, percentage}) => {

    return (
        <div>
            <button className="answer-button"
                    style={{
                        background: `linear-gradient(to right, #9305fa ${percentage * 0.8}%, #5c2cd3 ${percentage * 1.2}%)`,
                    }}
                    disabled={true}>
                <p style={{zIndex: 1, display: "inline-block"}}>{answer}</p>
                <p style={{zIndex: 10, display: "inline-block", float: "right"}}>{percentage}%</p>
            </button>
        </div>
    )
}

export default ResultComponent;