import React, {useState, useEffect} from 'react';
import {fetchLatestPoll, answerPoll} from '../service/pollService';
import AnswerComponent from "./AnswerComponent";
import ResultComponent from "./ResultComponent";

const PollComponent = () => {
    const [poll, setPoll] = useState([]);
    const [stats, setStats] = useState([]);

    useEffect(() => {
        getLatest();
    }, []);

    const getLatest = async () => {
        try {
            const data = await fetchLatestPoll();
            setPoll(data);
        } catch (error) {
            console.error('Failed to fetch poll', error);
        }
    };

    const handleAnswerPoll = async (pollId, answerId) => {
        try {
            const data = await answerPoll(pollId, answerId);
            setStats(data);
        } catch (error) {
            console.error('Failed to save message', error);
        }
    };

    if (stats !== undefined && stats.length !== 0) {
        return (
            <div className="poll">
                <div className="title">
                    <h1>Thank you for your response!</h1>
                </div>
                <div className="answer-container">
                    {poll.answers.map((answer) =>
                        stats.pollStatistics.answerStatistics.filter((stat) => stat.answerId === answer.id)
                            .map((stat) => (
                                    <div key={stat.answerId}><ResultComponent answer={answer.answer}
                                                                              answerId={stat.answerId}
                                                                              percentage={stat.percentage}/></div>
                                )
                            ))
                    }
                </div>
            </div>
        );
    } else if (poll === undefined || poll.question === undefined) {
        return <p>Loading...</p>
    } else {
        return (
            <div className="poll">
                <div className="title">
                    <h1>{poll.question}</h1>
                </div>
                <div className="answer-container">
                    {poll.answers.map((answer) => (
                        <div key={answer.id}><AnswerComponent onClick={e => handleAnswerPoll(poll.id, answer.id)}
                                                              answerId={answer.id} answer={answer.answer}
                                                              pollId={poll.id}/>
                        </div>
                    ))
                    }
                </div>
            </div>
        );
    }

};

export default PollComponent;