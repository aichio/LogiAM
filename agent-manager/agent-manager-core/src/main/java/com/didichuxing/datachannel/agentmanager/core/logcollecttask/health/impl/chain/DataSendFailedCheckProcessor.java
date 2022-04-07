package com.didichuxing.datachannel.agentmanager.core.logcollecttask.health.impl.chain;

import com.didichuxing.datachannel.agentmanager.common.chain.HealthCheckProcessorAnnotation;
import com.didichuxing.datachannel.agentmanager.common.constant.LogCollectTaskHealthCheckConstant;
import com.didichuxing.datachannel.agentmanager.common.enumeration.HealthCheckProcessorEnum;
import com.didichuxing.datachannel.agentmanager.common.enumeration.logcollecttask.LogCollectTaskHealthInspectionResultEnum;
import com.didichuxing.datachannel.agentmanager.common.enumeration.logcollecttask.LogCollectTaskHealthLevelEnum;
import com.didichuxing.datachannel.agentmanager.common.enumeration.metrics.AggregationCalcFunctionEnum;
import com.didichuxing.datachannel.agentmanager.common.enumeration.metrics.MetricFieldEnum;
import com.didichuxing.datachannel.agentmanager.core.logcollecttask.health.impl.chain.context.LogCollectTaskHealthCheckContext;
import com.didichuxing.datachannel.agentmanager.core.metrics.MetricsManageService;

/**
 * 是否存在采集端出口限流检查
 * @author william.
 */
@HealthCheckProcessorAnnotation(seq = 9, type = HealthCheckProcessorEnum.LOGCOLLECTTASK)
public class DataSendFailedCheckProcessor extends BaseProcessor {

    @Override
    protected void process(LogCollectTaskHealthCheckContext context) {
        /*
         * 校验日志采集任务是否为红 黄
         */
        if(
                context.getLogCollectTaskHealthLevelEnum().equals(LogCollectTaskHealthLevelEnum.RED) ||
                        context.getLogCollectTaskHealthLevelEnum().equals(LogCollectTaskHealthLevelEnum.YELLOW)
        ) {
            return;
        }
        /*
         * 校验 logcollecttask + logpath 在 host 端是否存在下游接收端写入失败
         */
        boolean dataSendFailedExists = checkDataSendFailedExists(
                context.getLogCollectTaskDO().getId(),
                context.getFileLogCollectPathDO().getId(),
                context.getHostDO().getHostName(),
                context.getMetricsManageService()
        );
        if (dataSendFailedExists) {//存在下游接收端写入失败
            context.setLogCollectTaskHealthLevelEnum(LogCollectTaskHealthInspectionResultEnum.DATA_SEND_FAILED_EXISTS.getLogCollectTaskHealthLevelEnum());
            String logCollectTaskHealthDescription = String.format(
                    "%s:LogCollectTaskId={%d}, FileLogCollectPathId={%d}, HostName={%s}",
                    LogCollectTaskHealthInspectionResultEnum.DATA_SEND_FAILED_EXISTS.getDescription(),
                    context.getLogCollectTaskDO().getId(),
                    context.getFileLogCollectPathDO().getId(),
                    context.getHostDO().getHostName()
            );
            context.setLogCollectTaskHealthDescription(logCollectTaskHealthDescription);
            context.setLogCollectTaskHealthInspectionResultEnum(LogCollectTaskHealthInspectionResultEnum.DATA_SEND_FAILED_EXISTS);
        }
    }

    /**
     * 校验 logcollecttask + logpath 在 host 端是否存在下游接收端写入失败
     *
     * @param logCollectTaskId     日志采集任务 id
     * @param fileLogCollectPathId 日志采集路径 id
     * @param hostName             主机名
     * @param metricsManageService MetricsManageService 对象
     * @return true：存在下游接收端写入失败 false：不存在下游接收端写入失败
     */
    private boolean checkDataSendFailedExists(
            Long logCollectTaskId,
            Long fileLogCollectPathId,
            String hostName,
            MetricsManageService metricsManageService
    ) {
        Long currentTime = System.currentTimeMillis();
        Long startTime = currentTime - LogCollectTaskHealthCheckConstant.DATA_SEND_FAILED_EXISTS_CHECK_LASTEST_MS_THRESHOLD;
        Object flushFailedTimesObj = metricsManageService.getAggregationQueryPerLogCollectTskAndPathAndHostNameFromMetricsLogCollectTask(
                logCollectTaskId,
                fileLogCollectPathId,
                hostName,
                startTime,
                currentTime,
                AggregationCalcFunctionEnum.SUM.getValue(),
                MetricFieldEnum.LOG_COLLECT_TASK_FLUSH_FAILED_TIMES.getFieldName()
        );
        Long flushFailedTimes = 0L;
        if(null != flushFailedTimesObj) {
            flushFailedTimes = Long.valueOf(flushFailedTimesObj.toString());
        }
        return flushFailedTimes > 0L;
    }

}
