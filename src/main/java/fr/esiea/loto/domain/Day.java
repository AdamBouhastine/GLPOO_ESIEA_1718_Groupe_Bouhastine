package fr.esiea.loto.domain;

public enum Day {

	LUNDI("LUNDI", "Lundi"), MARDI("MARDI   ", "Mardi"), MERCREDI("MERCREDI", "Mercredi"), JEUDI("JEUDI",
			"Jeudi"), VENDREDI("VENDREDI", "Vendredi"), SAMEDI("SAMEDI", "Samedi"), DIMANCHE("DIMANCHE", "Dimanche");

	private final String code;
	private final String label;

	private Day(String code, String label) {
		this.code = code;
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public static Day valueOfByCode(String code) throws IllegalArgumentException {
		for (Day value : values()) {
			String codee = value.getCode();
			if (codee.equalsIgnoreCase(code)) {
				return value;
			}
		}
		throw new IllegalArgumentException("The code " + code + "don't exist");
	}
}
