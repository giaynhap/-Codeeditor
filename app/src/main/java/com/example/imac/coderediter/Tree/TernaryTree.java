package com.example.imac.coderediter.Tree;


import java.util.ArrayList;
import java.util.List;

public class TernaryTree<T>
{
    private Node m_root = null;

    private Node Add(T s, int pos,   Node node)
    {
        if (node == null) {
            node = new Node(s.toString().charAt(pos), false);
        }

        if (s.toString().charAt(pos) < node.m_char) {

            node.m_left=  Add(s, pos,   node.m_left);
        }
        else if (s.toString().charAt(pos) > node.m_char) {

            node.m_right=  Add(s, pos,   node.m_right);
        }
        else
        {
            if (pos + 1 == s.toString().length()) {
                node.m_wordEnd = true;
                node.m_endString = s;
            }
            else {
                node.m_center = Add(s, pos + 1, node.m_center);
            }
        }
        return node;
    }

    public void Add(T s)
    {
        if (s == null || s == "") return  ;


        m_root =  Add(s, 0,  m_root);
    }

    public Node SearchTST(String s)
    {
        if (s == null || s == "")  return null;

        int pos = 0;
        Node node = m_root;
        while (node != null)
        {
            int cmp = s.charAt(pos) - node.m_char;
            if (s.charAt(pos) < node.m_char) { node = node.m_left; }
            else if (s.charAt(pos) > node.m_char) { node = node.m_right; }
            else
            {

                if (++pos == s.length()) return node;
                node = node.m_center;
            }
        }

        return null;
    }
    public void traverseTSTUtil(Node node, ArrayList<T> mList )
    {

        if (node ==null) return;

        this.traverseTSTUtil(node.m_left,mList);




        if (node.m_wordEnd)
        {
            mList.add((T)node.m_endString);
        }
        this.traverseTSTUtil(node.m_right,mList);

        this.traverseTSTUtil(node.m_center,mList);


    }
    public ArrayList<T> getListFromNode(String search)
    {
        ArrayList<T> rtn = new ArrayList<>();
        Node node = this.SearchTST(search);
        if (node == null) return rtn;
        this.traverseTSTUtil( node, rtn );
        return  rtn;

    }

}