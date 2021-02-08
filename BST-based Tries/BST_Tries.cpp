#include <bits/stdc++.h>
using namespace std;


class Node {
public:
    struct Link
    {
        char c;
        struct Link* left;
        struct Link* right;
        Node* next;
        Link(char ch) :c(ch),next(new Node), left(nullptr), right(nullptr) {}
    };
    Link* link = nullptr;
    bool isKey = false;

    // add char to the links within the node;
    void addchar(char ch) {
        if (!link) {
            link = new Link(ch);
        }
        else {
            Link* cur = link;
            while (cur) {
                if (ch<cur->c) {
                    if (!cur->left) {
                        cur->left = new Link(ch);
                        break;
                    }
                    else cur = cur->left;
                }
                else if (ch>cur->c) {
                    if (!cur->right) {
                        cur->right = new Link(ch);
                        break;
                    }
                    else cur = cur->right;
                }
                else break;
            }
        }
    }
    // check if the node's links contain the char ch;
    bool contain(char ch) {
        return getlink(ch);
    }
    // get the link with key ch in the node;
    Link* getlink(char ch) {
        Link* cur = link;
        while (cur) {
            if (ch<cur->c) cur = cur->left;
            else if (ch>cur->c) cur = cur->right;
            else return cur;
        }
        return nullptr;
    }

    void addstring(string key) {
        if (key.length()<1) {
            return;
        }
        int n = key.length();
        char ch = key[0];
        this->addchar(ch);
        Link* temp = getlink(ch);
        Node* cur = temp->next;
        if (n==1) {
            cur->isKey = true;
            return;
        }

        cur->addstring(key.substr(1,n-1));
    }

    bool iskey(string key) {
        int n = key.length();
        char ch = key[0];
        Link* temp = getlink(ch);
        if (!temp) {
            return false;
        }
        else {
            if (n==1) {
                return temp->next->isKey;
            }
            return temp->next->iskey(key.substr(1,n-1));
        }
    }
    vector<string> keywithprefix(string prefix) {
        vector<string> result;
        string s = prefix;
        Node* cur = this;
        while(s.length()) {
            Link* temp = cur->getlink(s[0]);
            if (!temp) return result;
            cur = temp->next;
            s = s.substr(1,s.length()-1);
            }
        dfs(cur,result,prefix);
        return result;
    }

    void qianxubianli (vector<char>& qianxu, Link* root) {
        if (root) qianxu.push_back(root->c);
        if (root->left) qianxubianli(qianxu,root->left);
        if (root->right) qianxubianli(qianxu,root->right);
    }
    void dfs(Node* cur, vector<string>& result, string& prefix) {
        if (cur->isKey) {
            result.push_back(prefix);
        }
        vector<char> qianxu;
        Link* pos = cur->link;
        if (pos) qianxubianli(qianxu,pos);
        else return;
        /*
        for (auto e: qianxu) {
            cout << e << ' ';
        }
        return;
        */
        for (auto ch:qianxu) {
            prefix.push_back(ch);
            Node* temp = cur->getlink(ch)->next;
            dfs(temp,result,prefix);
            prefix.pop_back();
        }
    }
};

/*
void addstring(string key) {
    if (key.length()<1) return;
    int n = key.length();
    for (int i=0; i<n; i++) {
        char ch = key[i];
        root->addchar(ch);
        Link* cur = root->getlink(ch);
        root = cur->next;
    }
}
*/

/*    bool contain(char ch) {
        if (!link) return false;
        Link* cur = link;
        while (cur) {
            if (ch<cur->c) cur = cur->left;
            else if (ch>cur->c) cur = cur->right;
            else return true;
        }
        return false;
    }
    */
int main()
{
    Node* tire = new Node();
    cout << tire->link << endl; // should be 0.
    tire->addchar('d');
    cout << tire->link << endl; // have an address.
    cout << tire->contain('d') << endl;  // should be true.
    cout << tire->contain('f') <<endl; // should be false;
    cout << tire->link->left << endl;
    cout << tire->link->right << endl;
    cout << tire->link->next << endl;
    cout << endl << "addstring and iskey test" << endl;
    tire->addstring("apple");
    cout << tire->iskey("afuck") << endl; // should be 0;
    cout << tire->iskey("apple") << endl; // should be 1;
    cout << endl << "key with prefix test" << endl;
    tire->addstring("appleman");
    tire->addstring("applegirl");
    tire->addstring("appledog");
    tire->addstring("apple girl");
    tire->addstring("apple US");
    tire->addstring("apple China");
    vector<string> result = tire->keywithprefix("apple");
    cout << result.size() << endl;
    for (auto e:result) {
        cout << e << ' ';
    }
    return 0;
}
