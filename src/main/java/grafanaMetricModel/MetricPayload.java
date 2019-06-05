package model.grafanaMetricModel;

import java.util.List;

public class MetricPayload {

    private MetricsDeviceMeta clientMeta;
    private List<KeyMetric> clientMetrics;

    public MetricsDeviceMeta getDeviceMeta() {
        return clientMeta;
    }

    public void setDeviceMeta(MetricsDeviceMeta deviceMeta) {
        this.clientMeta = deviceMeta;
    }

    public List<KeyMetric> getMetrics() {
        return clientMetrics;
    }

    public void setMetrics(List<KeyMetric> metrics) {
        this.clientMetrics = metrics;
    }
}
