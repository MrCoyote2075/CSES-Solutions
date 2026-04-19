#include <bits/stdc++.h>
using namespace std;

int solve(int n, int x, vector<int> arr)
{
    sort(arr.begin(), arr.end());

    int ans = 0;
    int l = 0, r = n - 1;

    while (l < r)
    {
        if (arr[l] + arr[r] <= x)
        {
            l++;
            r--;
        }
        else
            r--;
        
        ans++;
    }

    if (l == r)
        ans++;

    return ans;
}

int main()
{
    int n, x;
    cin >> n >> x;
    vector<int> arr(n);

    for (int i = 0; i < n; i++)
        cin >> arr[i];

    cout << solve(n, x, arr);
    return 0;
}