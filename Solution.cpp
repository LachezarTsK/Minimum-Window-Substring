
#include <vector>
#include <string>
using namespace std;

class Solution {
    
    inline static const string SUBSTRING_NOT_FOUND = "";

public:
    /*
    Alternatively C++20:
    #include <string_view>
    @declaration: string minWindow(string_view source, string_view target) const
    @return: source.substr(startIndexMinWindow, minWindowSize).data()
     */
    string minWindow(const string& source, const string& target) const {

        vector<int> targetFrequency('z' - 'A' + 1);
        for (int i = 0; i < target.length(); ++i) {
            ++targetFrequency[target[i] - 'A'];
        }

        int minWindowSize = INT_MAX;
        int startIndexMinWindow = 0;
        int countMatches = 0;
        int left = 0;
        int right = 0;

        while (right < source.length()) {
            if (--targetFrequency[source[right] - 'A'] >= 0) {
                ++countMatches;
            }

            if (countMatches == target.length()) {
                if (minWindowSize > right - left + 1) {
                    minWindowSize = right - left + 1;
                    startIndexMinWindow = left;
                }
                ++targetFrequency[source[left] - 'A'];
                ++left;
                --countMatches;
            }

            while (left <= right && targetFrequency[source[left] - 'A'] < 0) {
                ++targetFrequency[source[left] - 'A'];
                ++left;
            }
            ++right;

        }

        return minWindowSize < INT_MAX
                ? source.substr(startIndexMinWindow, minWindowSize)
                : SUBSTRING_NOT_FOUND;
    }
};
