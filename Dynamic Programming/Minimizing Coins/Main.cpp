#include <bits/stdc++.h>
using namespace std;

int solve(int x, vector<int> arr)
{
    if (x == 0)
        return 0;

    int ans = 1e9;
    
    for (int coin : arr) {
        if (x - coin >= 0)
            ans = min(ans, solve (x - coin, arr) + 1);
    }
    
    return ans;
}

int main()
{
    int n, x;
    cin >> n >> x;

    vector<int> arr(n);
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    int ans = solve(x, arr);

    cout << ((ans == 1e9) ? -1 : ans);
    return 0;
}