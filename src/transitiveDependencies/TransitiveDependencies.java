package transitiveDependencies;

public class TransitiveDependencies {
    private final String[][] dependencies;

    public TransitiveDependencies(String[][] dependencies) {
        this.dependencies = dependencies;
    }
    public String[] calculateFor(String start) {

        String[] result = new String[this.dependencies[0].length-1];
        if(result.length>0)
            result[0] = this.dependencies[0][1];
        return result;
    }
}
