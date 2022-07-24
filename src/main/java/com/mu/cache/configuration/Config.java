package com.mu.cache.configuration;

public class Config {
    private final int timeToLive;
    private final boolean keepStatistic;

    private Config(ConfigBuilder builder) {
        this.timeToLive = builder.timeToLive;
        this.keepStatistic = builder.keepStatistics;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public boolean isKeepStatistic() {
        return keepStatistic;
    }

    public static class ConfigBuilder {
        private final int timeToLive;
        private boolean keepStatistics = false;

        public ConfigBuilder(int timeToLive) {
            this.timeToLive = timeToLive;
        }

        public ConfigBuilder keepStatistics(boolean keepStatistics) {
            this.keepStatistics = keepStatistics;
            return this;
        }

        public Config build() {
            Config config = new Config(this);
            return config;
        }

    }

}
