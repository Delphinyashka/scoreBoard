import {standardButton} from "../pages/score-board/ScoreBoard.css";

export const startButtonColor = '#add777';
export const startButtonColorHovered = '#86dc3d';

export const finishButtonColor = '#ea5252';
export const finishButtonColorHovered = '#c50000';
export const modal = {
    content: {
        width: '50%',
        margin: 'auto',
        padding: '20px',
    },
};

export const startButton = {
    ...standardButton,
    background: startButtonColor,
};

export const finishButton = {
    ...standardButton,
    background: finishButtonColor,
};