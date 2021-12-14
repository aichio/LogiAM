package com.didichuxing.datachannel.system.metrcis.service;

import com.didichuxing.datachannel.system.metrcis.bean.DiskInfo;
import com.didichuxing.datachannel.system.metrcis.bean.NetCardInfo;
import com.didichuxing.datachannel.system.metrcis.bean.PeriodStatistics;
import com.didichuxing.datachannel.system.metrcis.bean.SystemMetrics;

import java.util.List;

/**
 * 系统级指标
 * @author william.hu
 */
public interface SystemMetricsService {

    /*********************** 总览 ***********************/

    /**
     * agent宿主机cpu核（逻辑核）
     */
    int CPU_NUM = Runtime.getRuntime().availableProcessors();

    /**
     * @return 返回源时钟与本地时钟的时间差（毫秒）
     */
    long getSystemNtpOffset();

    /**
     * @return 返回系统进程个数
     */
    int getSystemProcCount();

    /**
     * @return 返回系统启动时间
     */
    long getSystemStartupTime();

    /**
     * @return 返回系统运行时间
     */
    long getSystemUptime();

    /*********************** about process ***********************/

    /**
     * @return 返回当前不可中断的睡眠状态下的进程数
     */
    int getProcessesBlocked();

    /**
     * @return 返回当前可中断的睡眠状态下的进程数
     */
    int getProcessesSleeping();

    /**
     * @return 返回当前僵尸态进程数
     */
    int getProcessesZombies();

    /**
     * @return 返回当前暂停状态进程数
     */
    int getProcessesStopped();

    /**
     * @return 返回当前运行中的进程数
     */
    int getProcessesRunning();

    /**
     * @return 返回当前挂起的空闲进程数
     */
    int getProcessesIdle();

    /**
     * @return 返回当前等待中的进程数
     */
    int getProcessesWait();

    /**
     * @return 返回当前回收中的进程数
     */
    int getProcessesDead();

    /**
     * @return 返回当前分页进程数
     */
    int getProcessesPaging();

    /**
     * @return 返回当前未知状态进程数
     */
    int getProcessesUnknown();

    /**
     * @return 返回当前总进程数
     */
    int getProcessesTotal();

    /**
     * @return 返回当前总线程数
     */
    int getProcessesTotalThreads();

    /*********************** about cpu ***********************/

    /**
     * @return 返回系统CPU核心数
     */
    int getSystemCpuCores();

    /**
     * @return 返回系统总体CPU使用率(单位：%)
     * 注：使用率采用全核方式计数，如系统使用一颗核，则返回100，如使用两颗核，则返回200
     */
    PeriodStatistics<Double> getSystemCpuUtil();

    /**
     * @return 返回系统总体CPU使用率(单位：%)
     * 注意：使用率为总使用比率，系统使用一颗核，系统共10核，则返回0.1 = 10%
     */
    PeriodStatistics<Double> getSystemCpuUtilTotalPercent();

    /**
     * @return 返回内核态CPU时间占比(单位：%)
     */
    PeriodStatistics<Double> getSystemCpuSystem();

    /**
     * @return 返回用户态CPU时间占比(单位：%)
     */
    PeriodStatistics<Double> getSystemCpuUser();

    /**
     * @return 返回总体cpu空闲率（单位：%）
     */
    PeriodStatistics<Double> getSystemCpuIdle();

    /**
     * @return 返回cpu上下文交换次数
     */
    PeriodStatistics<Long> getSystemCpuSwitches();

    /**
     * @return 返回cpu处理硬中断的时间占比（单位：%）
     */
    PeriodStatistics<Double> getSystemCpuUsageIrq();

    /**
     * @return 返回cpu处理软中断的时间占比（单位：%），当前值、采样周期统计值
     */
    PeriodStatistics<Double> getSystemCpuUsageSoftIrq();

    /**
     * @return 返回系统近1分钟平均负载
     */
    PeriodStatistics<Double> getSystemLoad1();

    /**
     * @return 返回系统近5分钟平均负载
     */
    PeriodStatistics<Double> getSystemLoad5();

    /**
     * @return 返回系统近15分钟平均负载
     */
    PeriodStatistics<Double> getSystemLoad15();

    /**
     * @return 返回等待I/O的CPU时间占比(单位：%)
     */
    PeriodStatistics<Double> getSystemCpuIOWait();

    /**
     * @return 返回虚拟处理器CPU时间占比(单位：%)
     */
    PeriodStatistics<Double> getSystemCpuGuest();

    /**
     * @return 返回等待处理其他虚拟核的时间占比(单位：%)
     */
    PeriodStatistics<Double> getSystemCpuSteal();

    /*********************** about memory ***********************/

    /**
     * @return 返回系统当前可分配的内存总量（单位：byte）
     */
    long getSystemMemCommitLimit();

    /**
     * @return 返回系统已分配的包括进程未使用的内存量（单位：byte）
     */
    long getSystemMemCommittedAs();

    /**
     * @return 返回在磁盘分页文件上保留的物理内存量（单位：byte）
     */
    long getSystemMemCommitted();

    /**
     * @return 返回不能写入磁盘的物理内存量（单位：byte）
     */
    long getSystemMemNonPaged();

    /**
     * @return 返回没被使用是可以写入磁盘的物理内存量（单位：byte）
     */
    long getSystemMemPaged();

    /**
     * @return 返回用作共享内存的物理RAM量（单位：byte）
     */
    long getSystemMemShared();

    /**
     * @return 返回内核用来缓存数据结构供自己使用的内存量（单位：byte）
     */
    long getSystemMemSlab();

    /**
     * @return 返回系统物理内存总量（单位：byte）
     */
    long getSystemMemTotal();

    /**
     * @return 返回系统空闲内存大小（单位：byte）
     */
    long getSystemMemFree();

    /**
     * @return 返回系统已用内存大小（单位：byte）
     */
    long getSystemMemUsed();

    /**
     * @return 返回系统文件缓冲区的物理RAM量（单位：byte）
     */
    long getSystemMemBuffered();

    /**
     * @return 返回缓存内存的物理RAM量（单位：byte）
     */
    long getSystemMemCached();

    /**
     * @return 返回系统内存空闲率
     */
    double getSystemMemFreePercent();

    /**
     * @return 返回系统内存使用率
     */
    double getSystemMemUsedPercent();

    /**
     * @return 返回系统用作缓存的交换空间
     */
    long getSystemSwapCached();

    /**
     * @return 返回系统空闲swap大小（单位：byte）
     */
    long getSystemSwapFree();

    /**
     * @return 返回系统空闲swap占比
     */
    double getSystemSwapFreePercent();

    /**
     * @return 返回系统swap总大小（单位：byte）
     */
    long getSystemSwapTotal();

    /**
     * @return 返回系统已用swap大小（单位：byte）
     */
    long getSystemSwapUsed();

    /**
     * @return 返回系统已用swap占比（单位：%）
     */
    double getSystemSwapUsedPercent();

    /*********************** about disk、disk io ***********************/

    /**
     * @return 返回系统磁盘数
     */
    int getSystemDisks();

    /**
     * @return 返回系统各磁盘信息
     */
    List<DiskInfo> getSystemDiskInfoList();

    /*********************** about file handle ***********************/

    /**
     * @return 返回系统可以打开的最大文件句柄数
     */
    int getSystemFilesMax();

    /**
     * @return 返回系统已分配文件句柄数
     */
    int getSystemFilesAllocated();

    /**
     * @return 返回系统未分配文件句柄数
     */
    int getSystemFilesLeft();

    /**
     * @return 返回系统使用文件句柄占已分配百分比（单位：%）
     */
    double getSystemFilesUsedPercent();

    /**
     * @return 返回系统使用的已分配文件句柄数
     */
    int getSystemFilesUsed();

    /**
     * @return 返回系统未使用的已分配文件句柄数
     */
    int getSystemFilesNotUsed();

    /*********************** about network ***********************/

    /**
     * @return 返回系统网卡数
     */
    int getSystemNetCards();

    /**
     * @return 返回系统各网卡信息
     */
    List<NetCardInfo> getSystemNetCardInfoList();

    /**
     * @return 返回系统网络每秒下行流量
     */
    PeriodStatistics<Long> getSystemNetworkReceiveBytesPs();

    /**
     * @return 返回系统网络每秒上行流量
     */
    PeriodStatistics<Long> getSystemNetworkSendBytesPs();

    /**
     * @return 返回系统tcp连接数
     */
    Integer getSystemNetworkTcpConnectionNum();

    /**
     * @return 返回系统 Listening 状态的 tcp 链接数
     */
    Integer getSystemNetworkTcpListeningNum();

    /**
     * @return 返回系统处于 established 状态 tcp 连接数
     */
    Integer getSystemNetworkTcpEstablishedNum();

    /**
     * @return 返回系统处于 SYN_SENT 状态的 tcp 链接数
     */
    Integer getSystemNetworkTcpSynSentNum();

    /**
     *
     * @return 返回系统处于 SYN_RECV 状态的 tcp 链接数
     */
    Integer getSystemNetworkTcpSynRecvNum();

    /**
     * @return 返回系统处于 FIN_WAIT1 状态的 tcp 链接数
     */
    Integer getSystemNetworkTcpFinWait1Num();

    /**
     * @return 返回系统处于 FIN_WAIT2 状态的 tcp 链接数
     */
    Integer getSystemNetworkTcpFinWait2Num();

    /**
     * @return 返回系统处于 time wait 状态 tcp 连接数
     */
    Integer getSystemNetworkTcpTimeWaitNum();

    /**
     * @return 返回系统处于 closed 状态 tcp 连接数
     */
    Integer getSystemNetworkTcpClosedNum();

    /**
     * @return 返回系统处于 close wait 状态 tcp 连接数
     */
    Integer getSystemNetworkTcpCloseWaitNum();

    /**
     * @return 返回系统处于 closing 状态 tcp 连接数
     */
    Integer getSystemNetworkTcpClosingNum();

    /**
     * @return 返回系统处于 last ack 状态 tcp 连接数
     */
    Integer getSystemNetworkTcpLastAckNum();

    /**
     * @return 返回系统处于 none 状态 tcp 连接数
     */
    Integer getSystemNetworkTcpNoneNum();

    /**
     * @return 返回系统启动以来 Tcp 主动连接次数
     */
    Long getSystemNetworkTcpActiveOpens();

    /**
     * @return 返回系统启动以来 Tcp 被动连接次数
     */
    Long getSystemNetworkTcpPassiveOpens();

    /**
     * @return 返回系统启动以来 Tcp 连接失败次数
     */
    Long getSystemNetworkTcpAttemptFails();

    /**
     * @return 返回系统启动以来 Tcp 连接异常断开次数
     */
    Long getSystemNetworkTcpEstabResets();

    /**
     * @return 返回系统启动以来 Tcp 重传的报文段总个数
     */
    Long getSystemNetworkTcpRetransSegs();

    /**
     * @return 返回系统启动以来 Tcp 监听队列溢出次数
     */
    Long getSystemNetworkTcpExtListenOverflows();

    /**
     * @return 返回系统启动以来 UDP 入包量
     */
    Long getSystemNetworkUdpInDatagrams();

    /**
     * @return 返回系统启动以来 UDP 出包量
     */
    Long getSystemNetworkUdpOutDatagrams();

    /**
     * @return 返回系统启动以来 UDP 入包错误数
     */
    Long getSystemNetworkUdpInErrors();

    /**
     * @return 返回系统启动以来 UDP 端口不可达个数
     */
    Long getSystemNetworkUdpNoPorts();

    /**
     * @return 返回系统启动以来 UDP 发送缓冲区满次数
     */
    Long getSystemNetworkUdpSendBufferErrors();

    /**
     * @return 返回当前系统指标集 如须获取全量系统指标，请调用该方法而非挨个调用各指标获取函数以提升其性能、降低消耗
     */
    SystemMetrics getSystemMetrics();

}
