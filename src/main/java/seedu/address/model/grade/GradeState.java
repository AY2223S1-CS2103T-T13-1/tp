package seedu.address.model.grade;

/**
 * Represents a Grade State (graded or ungraded) in the list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class GradeState {

    public static final String MESSAGE_CONSTRAINTS =
            "Only true (graded) or false (ungraded)";
    public final boolean isGraded;
    /**
     * Constructs a {@code Name}.
     *
     * @param isGraded A valid task name.
     */
    public GradeState(boolean isGraded) {
        this.isGraded = isGraded;
    }

    @Override
    public String toString() {
        return isGraded ? "T" : "F";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeState // instanceof handles nulls
                && this.isGraded == (((GradeState) other).isGraded)); // state check
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isGraded);
    }
}
