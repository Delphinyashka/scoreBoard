export const getStatusLabel = (status) => {
    switch (status) {
        case 'LIVE':
            return 'Live';
        case 'FINISHED':
            return 'Finished';
        default:
            return 'Not Started';
    }
};

export const MIN_INT_VALUE = 0;

export function setInputValue(event, maxValue, changeFunction) {
    const value = Math.min(maxValue, Math.max(MIN_INT_VALUE, parseInt(event.target.value, 10) || MIN_INT_VALUE));
    if (event.target.value === '') {
        changeFunction(event.target.value);
    } else if (event.target.value !== MIN_INT_VALUE && event.target.value.toString().startsWith(String(MIN_INT_VALUE))) {
        changeFunction(MIN_INT_VALUE);
    } else {
        changeFunction(value);
    }
}