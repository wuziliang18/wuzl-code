package org.wuzl.test.algorithm;

import org.wuzl.test.comm.ListNode;
import org.wuzl.test.comm.ListNodeUtil;

/**
 * 查找中位数
 * 
 * @author ziliang.wu
 *
 */
public class FindLinkListMid {
    public static void main(String[] args) {
        ListNode<Integer> linkList = ListNodeUtil.getListNode(9);
        System.out.println("result:" + getMid(linkList));
        linkList = ListNodeUtil.getListNode(10);
        System.out.println("result:" + getMid(linkList));
    }

    public static <T> T getMid(ListNode<T> linkList) {
        if (linkList == null) {
            return null;
        }
        ListNode<T> slowNode = linkList;
        while (linkList != null) {
            linkList = linkList.getNext();
            if (linkList == null) {
                return slowNode.getVal();
            }
            linkList = linkList.getNext();
            if (linkList == null) {// 取当前值
                return slowNode.getVal();
            }
            slowNode = slowNode.getNext();
        }
        return slowNode.getVal();

    }
}
