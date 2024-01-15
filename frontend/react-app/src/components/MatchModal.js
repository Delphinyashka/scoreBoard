import React, {useState} from 'react';
import Modal from 'react-modal';
import axios from 'axios';
import {Title, Button} from '@mantine/core';
import {getStatusLabel, MIN_INT_VALUE, setInputValue} from "../services/ScoreBoardService";

import {
    finishButton,
    finishButtonColor, finishButtonColorHovered,
    modal,
    startButton,
    startButtonColor,
    startButtonColorHovered
} from "./MatchModal.css";
import {
    standardButton,
    input,
    teamsWrap,
    matchesHeader,
    standardButtonColor, standardButtonColorHovered
} from "../pages/score-board/ScoreBoard.css";

Modal.setAppElement('#root');
const MAX_TEAM_SCORE = 99;
const MatchModal = ({match, onClose, setMatches}) => {
    const [homeTeamScore, setHomeTeamScore] = useState(match.homeTeamScore);
    const [awayTeamScore, setAwayTeamScore] = useState(match.awayTeamScore);

    const handleUpdateHomeTeamScore = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/updateHomeTeamScore/`, {
                id: Number(match.id),
                homeTeamScore: Number(homeTeamScore),
            });
            setMatches(response.data);
        } catch (error) {
            console.error('Error updating home team score:', error);
        }
    };

    const handleUpdateAwayTeamScore = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/updateAwayTeamScore/`, {
                id: Number(match.id),
                awayTeamScore: Number(awayTeamScore),
            });
            setMatches(response.data);
        } catch (error) {
            console.error('Error updating away team score:', error);
        }
    };

    const handleStartMatch = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/startMatch/${match.id}`);
            setMatches(response.data);
        } catch (error) {
            console.error('Error starting match:', error);
        }
    };

    const handleFinishMatch = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/finishMatch/${match.id}`);
            setMatches(response.data);
        } catch (error) {
            console.error('Error finishing match:', error);
        }
    };

    return (
        <Modal isOpen={true} onRequestClose={onClose} contentLabel="Match Details" style={modal}>
            <div style={matchesHeader}>
                <Title order={4}>{`${match.homeTeam.name} vs ${match.awayTeam.name}`}</Title>
                <p>Status: {getStatusLabel(match.matchStatus)}</p>

                {match.matchStatus === 'LIVE' && (
                    <>
                        <div style={teamsWrap}>
                            <label htmlFor="homeTeamScore">Home Team Score: </label>
                            <input
                                type="number"
                                id="homeTeamScore"
                                value={homeTeamScore}
                                onChange={(e) => {
                                    setInputValue(e, MAX_TEAM_SCORE, setHomeTeamScore)
                                }}
                                onBlur={(e) => {
                                    if (e.target.value === '') {
                                        setHomeTeamScore(MIN_INT_VALUE);
                                    }
                                }}
                                style={input}
                            />
                            <Button onClick={handleUpdateHomeTeamScore} style={standardButton}
                                    onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = standardButtonColorHovered)}
                                    onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = standardButtonColor)}>
                                Update Home Score
                            </Button>
                        </div>

                        <div style={teamsWrap}>
                            <label htmlFor="awayTeamScore">Away Team Score: </label>
                            <input
                                type="number"
                                id="awayTeamScore"
                                value={awayTeamScore}
                                onChange={(e) => {
                                    setInputValue(e, MAX_TEAM_SCORE, setAwayTeamScore)
                                }}
                                onBlur={(e) => {
                                    if (e.target.value === '') {
                                        setAwayTeamScore(MIN_INT_VALUE);
                                    }
                                }}
                                style={input}
                            />
                            <Button onClick={handleUpdateAwayTeamScore} style={standardButton}
                                    onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = standardButtonColorHovered)}
                                    onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = standardButtonColor)}>
                                Update Away Score
                            </Button>
                        </div>
                    </>
                )}
                {match.matchStatus === 'FINISHED' && (
                    <p>{match.homeTeamScore} : {match.awayTeamScore}</p>
                )}

                {match.matchStatus === 'NOT_STARTED' && (
                    <Button onClick={handleStartMatch} style={startButton}
                            onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = startButtonColorHovered)}
                            onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = startButtonColor)}>
                        Start the Match
                    </Button>
                )}
                {match.matchStatus === 'LIVE' && (
                    <Button onClick={handleFinishMatch} style={finishButton}
                            onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = finishButtonColorHovered)}
                            onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = finishButtonColor)}>
                        Finish Match
                    </Button>
                )}
            </div>
        </Modal>
    );
};

export default MatchModal;
