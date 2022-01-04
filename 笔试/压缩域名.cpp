#include <iostream>
#include "string"

using namespace std;

string tackle(string target) {
    int length = (int) target.size();
    if (length != 39) {
        return "error";
    }
    for (int i = 0; i < 35; i += 5) {
        if (target[i] != ':') {
            return "error";
        }
    }
    int possible = 0;
    for (int block = 0; block < 8; block++) {   // 找到可能完全压缩的区域
        bool compress = true;
        for (int idx = block * 5; idx < block * 5 + 4; idx++) {
            if (target[idx] == ':') {
                return "error";
            }
            if (target[idx] != '0') {
                compress = false;
                break;
            }
        }
        if (compress) {
            possible ^= 1 << block;
        }
    }
    int compressing = 0, count = 0; // 确定需要完全压缩的区域
    for (int block = 0; block < 8; block++) {
        if ((possible & (1 << block)) != 0) {
            compressing ^= 1 << block;
            count++;
        } else if (count > 1) {
            break;
        } else {
            compressing = 0;
            count = 0;
        }
    }
    if (count == 8) {
        return "::";
    }
    int block = 0;
    string ans;
    while (block < 8) {
        if ((compressing & (1 << block)) != 0) {
            ans.append(":");
            block += count;
            continue;
        }
        if ((possible & (1 << block)) != 0) {
            ans.append("0:");
            block++;
            continue;
        }
        bool skip = true;
        for (int idx = block * 5; idx < block * 5 + 4; idx++) {
            if (target[idx] == ':') {
                return "error";
            }
            if (target[idx] == '0') {
                if (skip) {
                    continue;
                }
                ans += '0';
            } else {
                ans += target[idx];
                skip = false;
            }

        }
        if (block < 7) {
            ans.append(":");
        }
        block++;
    }
    return ans;
}

int main() {
    string str;
    cin >> str;

    cout << tackle(str) << endl;
}

