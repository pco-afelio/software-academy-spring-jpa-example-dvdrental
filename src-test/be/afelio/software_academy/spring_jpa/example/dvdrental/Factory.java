package be.afelio.software_academy.spring_jpa.example.dvdrental;

public class Factory {
	
	public static DvdRentalExampleSpringJpaRepository createDvdRentalExerciseRepository() {
		return null;
	}
	
    public static String getDatabaseUrl() {
        return "jdbc:postgresql://localhost:5432/dvdrental";
    }

    public static String getDatabaseUser() {
        return "postgres";
    }

    public static String getDatabasePassword() {
        return "postgres";
    }
	
}
