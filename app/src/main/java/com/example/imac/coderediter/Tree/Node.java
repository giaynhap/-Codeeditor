package com.example.imac.coderediter.Tree;

/**
 * Created by imac on 4/12/18.
 */

class Node<T>
{
      char m_char;
      Node m_left, m_center, m_right;
      T m_endString;
      boolean m_wordEnd;

    public Node(char ch, boolean wordEnd)
    {
        m_char = ch;
        m_wordEnd = wordEnd;
    }
    public String toString()
    {
        return m_char+"";
    }
}