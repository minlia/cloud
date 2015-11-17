package com.minlia.cloud.framework.common.constants;

public class Constants {

    public static final String TABLE_PREFIX = "minlia_";
    public static final String SEQUENCE_SUFFIX = "_sequence";
    public static final String SEQUENCE_GENERATOR = "sequenceGenerator";

    public final class Profiles {

        // Spring profile for development, production and "fast", see http://jhipster.github.io/profiles.html
        public static final String DEVELOPMENT = "dev";
        public static final String PRODUCTION = "prod";
        public static final String FAST = "fast";
        // Spring profile used when deploying with Spring Cloud (used when deploying to CloudFoundry)
        public static final String CLOUD = "cloud";
        // Spring profile used when deploying to Heroku
        public static final String HEROKU = "heroku";
        public static final String CLOUDFUNDRY = "cloudfundry";

        public static final String SYSTEM_ACCOUNT = "system";

        private Profiles() {
            throw new AssertionError();
        }
    }

    public Constants() {
        throw new AssertionError();
    }

}
