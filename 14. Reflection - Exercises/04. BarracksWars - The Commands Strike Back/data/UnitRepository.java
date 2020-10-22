package barracksWars.data;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import jdk.jshell.spi.ExecutionControl;

import java.util.Map;
import java.util.TreeMap;

public class UnitRepository implements Repository {

	private Map<String, Integer> amountOfUnits;

	public UnitRepository() {
		this.amountOfUnits = new TreeMap<>();
	}

	public void addUnit(Unit unit) {
		String unitType = unit.getClass().getSimpleName();
		if (!this.amountOfUnits.containsKey(unitType)) {
			this.amountOfUnits.put(unitType, 0);
		}

		int newAmount = this.amountOfUnits.get(unitType) + 1;
		this.amountOfUnits.put(unitType, newAmount);
	}

	public String getStatistics() {
		StringBuilder statBuilder = new StringBuilder();
		for (Map.Entry<String, Integer> entry : amountOfUnits.entrySet()) {
			String formatedEntry =
					String.format("%s -> %d%n", entry.getKey(), entry.getValue());
			statBuilder.append(formatedEntry);
		}
		statBuilder.setLength(
				statBuilder.length() - System.lineSeparator().length());

		return statBuilder.toString();
	}

	public String removeUnit(String unitType)  {
		String res = "";
		if (this.amountOfUnits.size() == 0) {
			res = "No such units in repository.";
		}
		for (Map.Entry<String, Integer> entry : this.amountOfUnits.entrySet()) {
			if (entry.getKey().equals(unitType) && entry.getValue() > 0) {
				entry.setValue(entry.getValue() - 1);
				res =  (unitType + " retired!");
				break;
			} else {
				res = ("No such units in repository.");
			}
		}
		return res;
	}
}
