package com.yinghua.core.utils.uuid;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ruoyi 序列生成类
 */
public class Seq
{
    // 通用序列类型
    public static final String commSeqType = "COMMON";

    // 上传序列类型
    public static final String uploadSeqType = "UPLOAD";

    // 通用接口序列数
    private static AtomicInteger commSeq = new AtomicInteger(1);

    // 上传接口序列数
    private static AtomicInteger uploadSeq = new AtomicInteger(1);

    // 机器标识
    private static final String machineCode = "A";

}
