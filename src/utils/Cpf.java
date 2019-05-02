package utils;

public class Cpf {

	public static String format(Long cpf) {
		StringBuilder str = new StringBuilder(cpf.toString());

		str.insert(str.length() - 8, ".");
		str.insert(str.length() - 5, ".");
		str.insert(str.length() - 2, "-");

		return str.toString();
	}

	public static boolean isValid(Long cpf) {
		StringBuilder str = new StringBuilder(cpf.toString());

		Integer soma = 0;

		if (str.length() != 11 || everbodyEquals(str)) {
			return false;
		}

		for (int i = 0, j = 10; i < 9; i++, j--) {
			soma = soma + (str.charAt(i) - 48) * j;
		}

		int aux = (soma * 10) % 11;

		if (aux == 10) {
			aux = 0;
		}

		if (aux != (str.charAt(9) - 48)) {
			return false;
		}

		soma = 0;

		for (int i = 0, j = 11; i < 10; i++, j--) {
			soma += (str.charAt(i) - 48) * j;
		}
		aux = (soma * 10) % 11;

		if (aux == 10) {
			aux = 0;
		}

		if (aux != (str.charAt(10) - 48)) {
			return false;
		}

		return true;
	}

	private static boolean everbodyEquals(StringBuilder str) {
		for (int i = 0; i < 10; i++) {
			if (str.charAt(i) != str.charAt(i + 1)) {
				return false;
			}
		}
		return true;
	}
}
