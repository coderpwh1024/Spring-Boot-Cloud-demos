package com.coderpwh.concurrency.example.Lock;

import java.util.concurrent.locks.StampedLock;

/***
 *  悲观锁与乐观锁实例
 */
public class LockExample4 {

    class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        void move(double deltaX, double deltaY) { // an exclusively locked methodlong stamp = sl.writeLock();
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        // 乐观锁案例
        double distanceFromOrigin() { // A read-only method
            long stamp = sl.tryOptimisticRead();    // 获取一个乐观锁
            double currentX = x, currentY = y;
            // 检查发出乐观读锁后是否有其他的写锁
            if (!sl.validate(stamp)) {
                // 如果没有，在此读悲观锁
                stamp = sl.readLock();
                try {
                    currentX = x;
                    currentY = y;
                } finally {
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + currentY * currentY);
        }

        //  悲观锁案例
        void moveIfAtOrigin(double newX, double newY) { // upgrade
            // Could instead start with optimistic, not read mode
            long stamp = sl.readLock();
            try {
                // 循环，检测当前状态是否符合
                while (x == 0.0 && y == 0.0) {
                    // 将读锁转化为写锁
                    long ws = sl.tryConvertToWriteLock(stamp);
                    // 确认转换为写锁是否成功
                    if (ws != 0L) {
                        // 如果成功，就替换票据
                        stamp = ws;
                        // 进行状态改变
                        x = newX;
                        y = newY;
                        break;
                    } else {  // 如果不成功就转化为写锁
                        sl.unlockRead(stamp);   // 显示释放读锁
                        stamp = sl.writeLock(); //  显示直接进行写锁，然后再通过循环再试
                    }
                }
            } finally {
                // 释放锁
                sl.unlock(stamp);
            }
        }
    }


}
