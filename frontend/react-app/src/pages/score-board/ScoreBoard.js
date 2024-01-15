import React, {useState, useEffect} from 'react';
import axios from 'axios';
import MatchModal from '../../components/MatchModal';
import {getStatusLabel, MIN_INT_VALUE, setInputValue} from "../../services/ScoreBoardService";
import {
    input,
    inputObject,
    matchesHeader,
    matchesWrap,
    matchObject,
    standardButton, standardButtonColor, standardButtonColorHovered, teamsWrap
} from "./ScoreBoard.css";

const MAX_TOTAL_SCORE = 198;

function ScoreBoard() {
    const [matches, setMatches] = useState([]);
    const [selectedMatch, setSelectedMatch] = useState(null);
    const [totalScore, setTotalScore] = useState('');

    useEffect(() => {
        fetchMatches();
    }, []);

    useEffect(() => {
        if (selectedMatch) {
            setSelectedMatch(
                matches.filter((match) => {
                    return match.id === selectedMatch.id;
                })[0]
            );
        }
    }, [matches]);

    const handleMatchClick = (match) => {
        setSelectedMatch(match);
    };

    const handleCloseModal = () => {
        setSelectedMatch(null);
    };

    const handleFilterByTotalScore = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/scoreBoardFiltered/${totalScore}`);
            setMatches(response.data);
        } catch (error) {
            console.error('Error filtering matches:', error);
        }
    };

    const fetchMatches = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/scoreBoard');
            setMatches(response.data);
        } catch (error) {
            console.error('Error fetching matches:', error);
        }
    };

    return (
        <div>
            <div>
                <h2 style={matchesHeader}>Matches</h2>
                <div
                    style={inputObject}>
                    <input
                        placeholder="Filter by total score"
                        value={totalScore}
                        onChange={(e) => {
                            setInputValue(e, MAX_TOTAL_SCORE, setTotalScore);
                        }}
                        onBlur={(e) => {
                            if (e.target.value === '') {
                                setTotalScore(MIN_INT_VALUE);
                            }
                        }}
                        type="number"
                        style={input}
                    />
                    <button
                        onClick={handleFilterByTotalScore}
                        style={standardButton}
                        onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = standardButtonColorHovered)}
                        onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = standardButtonColor)}
                    >
                        Filter
                    </button>
                    <button
                        onClick={fetchMatches}
                        style={standardButton}
                        onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = standardButtonColorHovered)}
                        onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = standardButtonColor)}
                    >
                        Show all matches
                    </button>
                </div>

                <div style={matchesWrap}>
                    {matches.map((match, index) => (
                        <div
                            key={index}
                            onClick={() => handleMatchClick(match)}
                            style={matchObject}
                            onMouseEnter={(e) => (e.currentTarget.style.backgroundColor = standardButtonColorHovered)}
                            onMouseLeave={(e) => (e.currentTarget.style.backgroundColor = 'initial')}
                        >
                            <h4 style={teamsWrap}>
                                {match.homeTeam.name} vs {match.awayTeam.name}
                            </h4>
                            {match.matchStatus !== 'NOT_STARTED' && (
                                <p>
                                    {match.homeTeamScore} : {match.awayTeamScore}
                                </p>
                            )}
                            <span style={teamsWrap}>{getStatusLabel(match.matchStatus)}</span>
                        </div>
                    ))}
                </div>
            </div>

            {selectedMatch && (
                <MatchModal match={selectedMatch} opened={!!selectedMatch} onClose={handleCloseModal}
                            setMatches={setMatches}/>
            )}
        </div>
    );
}

export default ScoreBoard;
