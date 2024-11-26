package com.moralok.algorithm4th.other;

public class SkipList {

    private static final int MAX_LEVEL = 16;
    private static final double PROBABILITY = 0.5;

    private final Node head = new Node();
    private int level;

    /**
     * Add value
     *
     * @param value value
     */
    public void add(int value) {
        // 随机生成节点的索引层级
        int level = randomLevel();
        // 创建新节点
        Node newNode = new Node();
        newNode.value = value;
        newNode.level = level;
        // updates 用于记录每一层需要插入新节点的位置，初始化为头节点
        Node[] updates = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            updates[i] = head;
        }
        // 查找每一层需要插入新节点的位置
        Node cur = head;
        for (int i = level - 1; i >= 0; i--) {
            while (cur.forwards[i] != null && cur.forwards[i].value < value) {
                cur = cur.forwards[i];
            }
            updates[i] = cur;
        }
        // 插入节点
        for (int i = level - 1; i >= 0; i--) {
            newNode.forwards[i] =  updates[i].forwards[i];
            updates[i].forwards[i] = newNode;
        }
        // 如果跳表当前的层级小于新节点的索引层级，则更新
        if (this.level < level) {
            this.level = level;
        }
    }

    /**
     * Get value
     *
     * @param value target
     * @return node if target exists, null if not exists
     */
    public Node get(int value) {
        // 从头节点开始
        Node cur = head;
        // 从最顶层开始查找
        for (int i = level - 1; i >= 0; i--) {
            // 遍历当前层，找到小于目标值的最大值所在的节点
            while (cur.forwards[i] != null && cur.forwards[i].value < value) {
                cur = cur.forwards[i];
            }
            // 判断后继节点是否为目标
            // 如果不是，下一次循环时 i--，代表指针向下层移动
            if (cur.forwards[i] != null && cur.forwards[i].value == value) {
                return cur.forwards[i];
            }
        }
        return null;
    }

    /**
     * Delete target
     *
     * @param value target
     */
    public void delete(int value) {
        // 定位待删除节点的位置
        Node cur = head;
        Node[] updates = new Node[level];
        for (int i = level - 1; i >= 0; i--) {
            while (cur.forwards[i] != null && cur.forwards[i].value < value) {
                cur = cur.forwards[i];
            }
            updates[i] = cur;
        }
        // 检查目标节点是否存在
        if (cur.forwards[0] != null && cur.forwards[0].value == value) {
            for (int i = level - 1; i >= 0; i--) {
                if (updates[i].forwards[i] != null && updates[i].forwards[i].value == value) {
                    updates[i].forwards[i] = updates[i].forwards[i].forwards[i];
                }
            }
        }
        // 如果删除的目标节点是唯一最高节层，则减少层级
        while (level > 1 && head.forwards[level - 1] == null) {
            level--;
        }
    }

    /**
     * Generate the level of node randomly.
     *
     * @return level
     */
    private int randomLevel() {
        int level = 1;
        while (Math.random() > PROBABILITY && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public void printAll() {
        Node cur = head;
        while (cur.forwards[0] != null) {
            System.out.println(cur.forwards[0]);
            cur = cur.forwards[0];
        }
    }

    public static class Node {

        private int value;
        private final Node[] forwards = new Node[MAX_LEVEL];
        private int level;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", level=" + level +
                    '}';
        }
    }
}
