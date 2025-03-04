const letters = 'abcdefghijklmnopqrstuvwxyz'.split('');
const numbers = '0123456789'.split('');
const special = '!@#$%^&*()+-_.,?|~`'.split('');

export function checkPassword(password) {
    let length = true, letterStart = true, hasANum = false, hasASpecial = false, hasASpace = false;
    
    if (password.length < 8) {
        length = false;
    }
    
    if (!isLetter(password.charAt(0).toLowerCase())) {
        letterStart = false;
    }

    for (const char of password) {
        if (isNumber(char)) {
            hasANum = true;
        } else if (char === ' ') {
            hasASpace = true;
        } else if (isSpecial(char)) {
            hasASpecial = true;
        }
    }

    let errorString = "";
    if (!length) errorString += "minimum 8 characters,";
    if (!letterStart) errorString += " start with a letter,";
    if (!hasANum) errorString += " a number,";
    if (hasASpace) errorString += " no spaces,";
    if (!hasASpecial) errorString += " 1 special character,";

    return errorString;
}

function isLetter(char) {
    return letters.includes(char);
}

function isNumber(char) {
    return numbers.includes(char);
}

function isSpecial(char) {
    return special.includes(char);
}
