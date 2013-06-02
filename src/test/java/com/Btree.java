package com;

/**
 * Created with IntelliJ IDEA.
 * User: ye
 * Date: 13-5-30
 * Time: 下午10:22
 * B-tree:机械硬盘经典索引
 */
public class Btree {
    static int degree = 5;

    class Record {
        int key;
    }

    class Node {
        int n;
        Record[] rec = new Record[2 * degree - 1];//完全仿照硬盘体系，一上来就刷满整个页块
        Node[] child = new Node[2 * degree];
        Node parent;
        boolean leaf = false;//默认内部节点吧
    }

    class SearchResult {
        Node n;
        int start;
        int end;

        SearchResult(Node n, int start, int end) {
            this.n = n;
            this.start = start;
            this.end = end;
        }
    }

    SearchResult search(Node x, int key) {
        int i = 0;
        int j = x.n - 1;
        for (; i < x.n; i++) {
            if (x.rec[i].key >= key) {
                break;
            }
        }
        int mid = -1, upMid = -1, downMid = -1;
        while (i < j) {
            mid = i + (j - i) / 2;
            if (x.rec[mid - 1].key == key) {
                if (x.rec[mid - 1].key == key) {
                    int upi = i;
                    int upj = mid;
                    while (upi < upj) {
                        downMid = upi + (upj - upi) / 2;
                        if (x.rec[downMid].key == key) {
                            upj = downMid;
                        } else {
                            upi = downMid;
                        }
                    }
                }
                if (x.rec[mid + 1].key == key) {
                    int upi = mid;
                    int upj = j;
                    while (upi < upj) {
                        upMid = upi + (upj - upi) / 2;
                        if (x.rec[upMid].key == key) {
                            upi = upMid;
                        } else {
                            upj = upMid;
                        }
                    }
                }
                return new SearchResult(x, downMid+1, upMid);
            }
            if (x.rec[mid].key > key) {
                j = mid;
            } else {
                i = mid;
            }
        }
        if (x.leaf)
            return null;
        return search(x.child[mid + 1], key);
    }

    void bisection() {

    }

    /**
     * 分裂指定节点的满节点孩子
     *
     * @param x    父节点
     * @param full 满孩子节点的下标
     */
    void split(Node x, int full) {
        Node z = new Node();
        Node y = x.child[full];
        int i = degree, j = 0;
        for (; i < 2 * degree - 1; i++, j++) {
            z.rec[j] = y.rec[i];
            z.child[j] = y.child[i];
        }
        z.child[j] = y.child[i];
        z.n = y.n = degree - 1;
        z.leaf = y.leaf;
        //------------backup ok,let's offset Node x--------------
        int k = x.n;
        for (; k > full - 1; k--) {
            x.rec[k + 1] = x.rec[k];
            x.child[k + 2] = x.child[k + 1];
        }
        x.rec[full] = y.rec[degree - 1];
        x.child[full + 1] = z;
        x.n++;
    }

    /**
     * 在非满节点上完成插入
     *
     * @param x      非满节点
     * @param record 待插入的记录
     * @return
     */
    Record insert_nonefull(Node x, Record record) {
        if (x.leaf) {
            int i = x.n;
            for (; x.rec[i - 1].key > record.key && i > -1; i--) {
                x.rec[i] = x.rec[i - 1];
            }
            if (x.rec[i].key == record.key) {
                return x.rec[i];
            }
            x.rec[i] = record;
        } else {
            int i = x.n;
            for (; x.rec[i - 1].key > record.key && i > -1; i--) {
            }
            i = i + 1;
            if (x.child[i].n == 2 * degree - 1) {
                split(x, i);
                if (x.rec[i].key < record.key) {
                    i = i + 1;
                }
            }
            insert_nonefull(x.child[i], record);
        }
        return record;
    }

    /**
     * 从根节点开始自顶向下插入
     *
     * @param root
     * @param record
     * @return 根节点
     */
    Node insert(Node root, Record record) {
        if (root.n == 2 * degree - 1) {
            Node newRoot = new Node();
            newRoot.child[0] = root;
            split(newRoot, 0);
            insert_nonefull(newRoot, record);
            return newRoot;
        } else {
            insert_nonefull(root, record);
            return root;
        }
    }
}
