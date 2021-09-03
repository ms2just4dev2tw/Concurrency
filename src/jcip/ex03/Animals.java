package jcip.ex03;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <h6>CodeList 3-9 Animals</h6> <i>Thread confinement of local primitive and
 * reference variables</i>
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Animals {

	Ark ark;
	Gender gender;
	Species species;

	public int loadTheArk(Collection<Animal> candidates) {
		int numPairs = 0;
		Animal candidate = null;
		SortedSet<Animal> animals;

		// animals confined to method, don't let them escape!
		// 将animals封闭在栈中，不要让它逸出
		animals = new TreeSet<Animal>(new SpeciesGenderComparator());
		animals.addAll(candidates);

		for (Animal a : animals) {
			if (candidate == null || !candidate.isPotentialMate(a))
				candidate = a;
			else {
				ark.load(new AnimalPair(candidate, a));
				++numPairs;
				candidate = null;
			}
		}
		// 任何方法都无法获得基本类型的引用
		return numPairs;
	}

	enum Gender {
		MALE, FEMALE
	}

	enum Species {
		AARDVARK, BENGAL_TIGER, CARIBOU, DINGO, ELEPHANT, FROG, GNU, HYENA, IGUANA, JAGUAR, KIWI, LEOPARD, MASTADON,
		NEWT, OCTOPUS, PIRANHA, QUETZAL, RHINOCEROS, SALAMANDER, THREE_TOED_SLOTH, UNICORN, VIPER, WEREWOLF,
		XANTHUS_HUMMINBIRD, YAK, ZEBRA
	}

	class Ark {
		private final Set<AnimalPair> loadedAnimals = new HashSet<AnimalPair>();

		public void load(AnimalPair pair) {
			loadedAnimals.add(pair);
		}
	}

	class Animal {
		Gender gender;
		Species species;

		public boolean isPotentialMate(Animal other) {
			return species == other.species && gender != other.gender;
		}
	}

	class AnimalPair {
		@SuppressWarnings("unused")
		private final Animal one, two;

		public AnimalPair(Animal one, Animal two) {
			this.one = one;
			this.two = two;
		}
	}

	class SpeciesGenderComparator implements Comparator<Animal> {
		public int compare(Animal one, Animal two) {
			int speciesCompare = one.species.compareTo(two.species);
			return (speciesCompare != 0) ? speciesCompare : one.gender.compareTo(two.gender);
		}
	}
}
