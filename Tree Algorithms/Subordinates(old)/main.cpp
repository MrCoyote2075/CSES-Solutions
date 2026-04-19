#include<bits/stdc++.h>
using namespace std;
 
vector<int> sub_tree;
 
vector<vector<int>> adj;
void dfs(int u , int par) {
 
 
    for(int v : adj[u] ) {
        if(v == par) continue;
        dfs( v , u ) ;
        
        sub_tree[u-1] += 1 ;   
        sub_tree[u-1]+=sub_tree[v-1];
    }
 
}
 
void solve(){
 
    int n ;
     cin>>n;
    sub_tree.resize(n,0);
    adj.resize(n+1);
    
    for(int u = 2 ;u <= n ; u += 1 )
    {
        int v ;
        cin>>v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
 
    dfs( 1 , -1 );
 
    for(int  a  : sub_tree){
        cout<<a<<" ";
    }
 
}
 
int main(){
 
    int t =1 ;
    while(t--){
        solve();
    }
 
    return 0;
}