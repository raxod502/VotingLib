package voting;

public abstract class Person {
    
    private static int personSerialNumberCounter = 1;
    protected final int personSerialNumber;
    
    protected final double[] preferences;
    
    public Person(double[] preferences) {
        this.preferences = preferences;
        this.personSerialNumber = personSerialNumberCounter++;
    }
    
    public double[] getPreferences() {
        return preferences;
    }
    
    @Override
    public String toString() {
        return "Person #" + personSerialNumber;
    }
    
}
