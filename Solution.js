
/**
 * @param {string} source
 * @param {string} target
 * @return {string}
 */
var minWindow = function (source, target) {
    const SUBSTRING_NOT_FOUND = "";
    const ascii_code_for_A = 65;
    const ascii_code_for_z = 122;

    const targetFrequency = new Array(ascii_code_for_z - ascii_code_for_A + 1).fill(0);
    for (let i = 0; i < target.length; ++i) {
        ++targetFrequency[target.codePointAt(i) - ascii_code_for_A];
    }

    let minWindowSize = Number.MAX_SAFE_INTEGER;
    let startIndexMinWindow = 0;
    let countMatches = 0;
    let left = 0;
    let right = 0;

    while (right < source.length) {
        if (--targetFrequency[source.codePointAt(right) - ascii_code_for_A] >= 0) {
            ++countMatches;
        }

        if (countMatches === target.length) {
            if (minWindowSize > right - left + 1) {
                minWindowSize = right - left + 1;
                startIndexMinWindow = left;
            }
            ++targetFrequency[source.codePointAt(left) - ascii_code_for_A];
            ++left;
            --countMatches;
        }

        while (left <= right && targetFrequency[source.codePointAt(left) - ascii_code_for_A] < 0) {
            ++targetFrequency[source.codePointAt(left) - ascii_code_for_A];
            ++left;
        }
        ++right;
    }

    return minWindowSize < Number.MAX_SAFE_INTEGER
            ? source.substring(startIndexMinWindow, startIndexMinWindow + minWindowSize)
            : SUBSTRING_NOT_FOUND;
};
