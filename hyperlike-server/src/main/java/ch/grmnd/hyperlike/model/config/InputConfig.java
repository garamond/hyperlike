package ch.grmnd.hyperlike.model.config;

/**
 * Configuration of a survey input values; aggregated by Config.
 */
public class InputConfig {

    private final String id;
    private final String question;
    private final Double min;
    private final Double max;
    private final Double step;
    private final Double initial;

    private InputConfig() {
        id = null;
        question = null;
        min = null;
        max = null;
        step = null;
        initial = null;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }

    public Double getStep() {
        return step;
    }

    public Double getInitial() {
        return initial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputConfig that = (InputConfig) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (min != null ? !min.equals(that.min) : that.min != null) return false;
        if (max != null ? !max.equals(that.max) : that.max != null) return false;
        if (step != null ? !step.equals(that.step) : that.step != null) return false;
        return !(initial != null ? !initial.equals(that.initial) : that.initial != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (min != null ? min.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (initial != null ? initial.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InputConfig{" +
                "question='" + question + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", step=" + step +
                ", initial=" + initial +
                '}';
    }

}
