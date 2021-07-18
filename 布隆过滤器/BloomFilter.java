package com.mj.布隆过滤器;

public class BloomFilter<T> {
    //位数
    private int bitSize;
    //位数组
    private long[] bits;
    //哈希函数个数
    private int hashNum;

    /**
     * @param n 数据规模
     * @param p 误判率
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) { throw new IllegalArgumentException("n must be > 0,q must between 0 and 1"); }
        double ln2 = Math.log(2);
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));

        hashNum = (int) (bitSize * ln2 / n);
        //这个公式向上取整
        bits = new long[(bitSize + Long.SIZE - 1) / Long.SIZE];

    }

    public void put(T value) {
        nullCheck(value);

        //利用value生成2个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 0; i < hashNum; i++) {
            int combinedHash = hash1 + (i * hash2);
            //生成非负数
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            setBit(combinedHash % bitSize);
        }
    }

    /**
     * 设置二进制位
     *
     * @param index
     */
    private boolean setBit(int index) {
        //找到数组中对应的位置
        long value = bits[index / Long.SIZE];
        //将对应位置设为1
        long bitValue = 1L << (index % Long.SIZE);
        bits[index / Long.SIZE] |= bitValue;
        return (value & bitValue) == 0;

    }

    /**
     * 查看index位置二进制位
     *
     * @param index
     * @return
     */
    private boolean getBit(int index) {
        return (bits[index / Long.SIZE] & (1L << (index % Long.SIZE))) != 0;
    }

    public boolean contains(T value) {
        nullCheck(value);
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;

        for (int i = 0; i < hashNum; i++) {
            int combinedHash = hash1 + (i * hash2);
            //生成非负数
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            if (!getBit(combinedHash % bitSize)) {
                return false;
            }
        }
        return true;
    }

    private void nullCheck(T value) {
        if (value == null) {
            throw new IllegalArgumentException("value can't be null!");
        }
    }
}
