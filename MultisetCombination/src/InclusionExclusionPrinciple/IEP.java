package InclusionExclusionPrinciple;

import java.util.Arrays;

public final class IEP {
    private IEP() {
    }

    private static long fact(long k) {//求k的阶乘
        if (k == 0) {
            return 1;
        } else {
            long sum = 1;
            for (int i = 1; i <= k; ++i) {
                sum *= i;
            }
            return sum;
        }

    }

    private static long C(long total, long choose) {//求组合数
        if (choose < 0 || total < choose) {
            return 0;
        } else {
            return fact(total) / (fact(choose) * fact(total - choose));
        }
    }

    private static long F(long total, long choose) {//求重集组合数
        return C(total + choose - 1, choose);
    }

    private static byte Xi(int num, int k) {//获取第k位的bit
        return (byte) ((num >> k) & 1);
    }

    public static Integer compute(Integer r, Integer... k) {
        int sum = 0;
        for (Integer i : k) {
            if (i <= 0) {
                return -1;
            }
            sum += i;
        }
        if (sum < r) {
            return -1;
        }

        int n = k.length;//集合的数量F
        long Cr = F(n, r);

        for (int i = 1; i <= Math.pow(2, n) - 1; ++i) {//第2步
            long S1 = 0, S2 = 0;
            for (int j = 0; j < n; ++j) {//2.2
                S1 += Xi(i, j) * k[j];
            }
            for (int j = 0; j < n; ++j) {//2.3
                S2 += Xi(i, j);
            }

            if (S2 % 2 == 0) {//2.4 2.5
                Cr += F(n, (r - S1 - S2));
            } else {
                Cr -= F(n, (r - S1 - S2));

            }
        }
        return (int) Cr;
    }
}