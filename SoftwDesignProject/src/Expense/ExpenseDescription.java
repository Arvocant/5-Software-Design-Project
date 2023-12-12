package Expense;

public class ExpenseDescription {
    private String name;
    private String notes;

    public ExpenseDescription(String name, String notes) {
        this.name = name;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Title of expense: " + name + "\n      Note: " + notes;
    }
}

