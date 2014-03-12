#include "stdafx.h"
#include <string>
#include <deque>
#include <algorithm>
using namespace std;

string convert(string s, int nRows) {
	string answer;
	int step = 2 * (nRows - 1);
	// first 
	for (int i = 0; i < nRows; ++i) {
		for (int j = i; j < s.length(); j += step) {
			answer.push_back(s[j]);
			if ((i > 0) && (i < (nRows - 1))) {
				int second = j + step - i * 2;
				if (second < s.length()) {
					answer.push_back(s[second]);
				}
			}
		}
	}
	return answer;
}

	int mysqrt(int x) {
		if (x <= 0) {
			return 0;
		}

		int digits = 0;
		int xx = x;
		while (xx > 0) {
			digits++;
			xx = xx >> 2;
		}

		int answer = 0;
		for (; digits > 0; digits--) {
			answer = answer << 1;
			int xx = (x >> ((digits - 1) * 2)) - answer * answer;
			if (xx > 2 * answer) {
				answer++;
			}
		}
		return answer;
	}

	int candy(vector<int> &ratings) {
		if (ratings.size() <= 0) {
			return 0;
		}

		vector<int> candy(ratings.size(), 0);
		candy[0] = 1;
		for (int i = 1; i < ratings.size(); ++i) {
			if (ratings[i] > ratings[i - 1]) {
				candy[i] = candy[i - 1] + 1;
			} else {
				candy[i] = 1;
			}
		}

		for (int i = ratings.size() - 2; i >= 0; --i) {
			if ((ratings[i] > ratings[i + 1]) && (candy[i] <= candy[i+1])) {
				candy[i] = candy[i + 1] + 1;
			}
		}

		int answer = 0;
		for (int i = 0; i < candy.size(); ++i) {
			answer += candy[i];
		}
		return answer;
	}

	vector<vector<int> > subsets(vector<int> &S) {
		vector<vector<int> > answers;
		int n = (1 << S.size());
		sort(S.begin(), S.end());
		for (int i = 0; i < n; ++i) {
			vector<int> answer;
			for (int j = 0; j < S.size(); ++j) {
				int mask = 1 >> j;
				if (i & mask) {
					answer.push_back(S[j]);
				}
			}
			answers.push_back(answer);
		}
		return answers;
	}

	int singleNumber(int A[], int n) {
		int intSize = sizeof(int) * 8;
		vector<int> count(intSize, 0);
		for (int i = 0; i < n; ++i) {
			int mask = 1;
			for (int j = 0; j < intSize; ++j) {
				if (mask & A[i]) {
					count[j] = (count[j] + 1) % 3;
				}
				mask = mask << 1;
			}
		}

		int answer = 0;
		for (int j = 0; j < intSize; ++j) {
			if (count[j]) {
				answer += (1 << j);
			}
		}
		return answer;
	}

	bool compute(deque<int>& values, char operand) {
		if (values.size() < 2) {
			return false;
		}
		int value2 = values.back();
		values.pop_back();
		int value1 = values.back();
		values.pop_back();
		switch (operand) {
		case '+':
			values.push_back(value1 + value2);
			break;
		case '-':
			values.push_back(value1 - value2);
			break;
		case '*':
			values.push_back(value1 * value2);
			break;
		case '/':
			values.push_back(value1 / value2);
			break;
		default:
			return false;
		}
		return true;

	}
	int evalRPN(vector<string> &tokens) {
		deque<int> values;
		for (int i = 0; i < tokens.size(); ++i) {
			string token = tokens[i];
			if (token.compare("+") == 0) {
				if (!compute(values, '+')) {
					return 0;
				}
			}
			else if (token.compare("-") == 0) {
				if (!compute(values, '-')) {
					return 0;
				}
			}
			else if (token.compare("*") == 0) {
				if (!compute(values, '*')) {
					return 0;
				}
			}
			else if (token.compare("/") == 0) {
				if (!compute(values, '/')) {
					return 0;
				}
			}
			else {
				int operand = atoi(token.c_str());
				values.push_back(operand);
			}
		}

		if (values.size() <= 0) {
			return 0;
		}
		else {
			return values.back();
		}
	}
