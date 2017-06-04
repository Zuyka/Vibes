
package State;
import java.util.Arrays;

public final class ExperimentState {
    
    private final ExperimentSettings settings;
    private final Particle[] particles;
    private long time; // microseconds since the beginning of experiment

    private ExperimentState(final Builder builder) {
        settings = builder.settings;
        particles = builder.particles;
        time = builder.time;
    }

    public ExperimentSettings getSettings() {
        return settings;
    }

    public Particle[] getParticles() {
        return particles;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExperimentState that = (ExperimentState) o;

        if (time != that.time) return false;
        if (!settings.equals(that.settings)) return false;
        return Arrays.equals(particles, that.particles);

    }

    @Override
    public int hashCode() {
        int result = settings.hashCode();
        result = 31 * result + Arrays.hashCode(particles);
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private ExperimentSettings settings;
        private Particle[] particles;
        private long time;

        public Builder particles(final Particle[] particles) {
            this.particles = particles;

            return this;
        }

        public Builder settings(final ExperimentSettings settings) {
            this.settings = settings;

            return this;
        }

        public Builder time(final long microseconds) {
            time = microseconds;

            return this;
        }

        public ExperimentState build() {
            return new ExperimentState(this);
        }
    }
}
