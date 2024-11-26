package com.moralok.algorithm4th.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkipListTests {

    @Test
    void addAndDelete() {
        SkipList skipList = new SkipList();
        for (int i = 0; i < 100; i++) {
            skipList.add(i);
        }
        System.out.println("输出添加结果: ");
        skipList.printAll();

        SkipList.Node node = skipList.get(20);
        System.out.println("查询结果: " + node);

        skipList.delete(20);
        System.out.println("删除结果: ");
        skipList.printAll();

        assertNull(skipList.get(20));
    }
}