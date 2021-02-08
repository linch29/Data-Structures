#include <bits/stdc++.h>
using namespace std;

class UnionFind {
public:
    vector<int> parent;

    UnionFind(int n) {
        parent = vector<int>(n,-1);
    }

    int sizeOf(int vertex) {
        return -parent[findparent(vertex)];
    }

    bool isconnected(int v1, int v2) {
        return findparent(v1)==findparent(v2);
    }

    int findparent(int vertex) {
        int temp = vertex;
        int temp2;
        while (parent[temp]>-1) {
            temp = parent[temp];
        }
        while (vertex!=temp) {
            temp2 = parent[vertex];
            parent[vertex] = temp;
            vertex = temp2;
        }
        return temp;
    }

    void Union(int v1, int v2) {
        int v1root = findparent(v1);
        int v2root = findparent(v2);
        if (v1root==v2root) return;
        else {
            if (sizeOf(v1)>sizeOf(v2)) {
                parent[v1root] -= sizeOf(v2);
                parent[v2root] = v1root;
            }
            else {
                parent[v2root] -= sizeOf(v1);
                parent[v1root] = v2root;
            }
        }
    }
};

int main()
{
    UnionFind a = UnionFind(10);
    a.Union(0,1);
    a.Union(1,2);
    a.Union(5,6);
    cout << a.isconnected(0,2) << endl;
    cout << a.sizeOf(0) << endl;
    a.Union(1,5);
    cout << a.sizeOf(0) << endl;
}
