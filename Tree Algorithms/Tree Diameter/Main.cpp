#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> grp;
int ans = 0;

int rec(int u, int par)
{
    int max1 = 0;
    int max2 = 0;

    for (int v : grp[u])
    {
        if (v != par)
        {
            int val = rec(v, u) + 1;

            if (val > max1)
            {
                max2 = max1;
                max1 = val;
            }
            else if (val > max2)
            {
                max2 = val;
            }
        }
    }

    ans = max(ans, max1 + max2);

    return max1;
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;

    grp.resize(n + 1);

    for (int i = 1; i < n; i++)
    {
        int u, v;
        cin >> u >> v;

        grp[u].push_back(v);
        grp[v].push_back(u);
    }

    rec(1, 0);

    cout << ans << "\n";

    return 0;
}
